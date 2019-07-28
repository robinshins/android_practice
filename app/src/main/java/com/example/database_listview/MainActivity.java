package com.example.database_listview;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {
    public long epoch = System.currentTimeMillis();
    public String Timestamp = Long.toString(epoch);
    TextView textView;
    EditText editText ;// 이름
    EditText editText2 ;// 전화번호
    EditText editText3 ;// 생년월일
    EditText editText4; // 비밀번호
    EditText editText5; // 비밀번호 확인
    EditText editText6; // 인증번호
    RadioButton radioButton ;//남성
    RadioButton radioButton2 ;//여성
    RadioGroup radioGroup;
    String proving_number = null;
    TextView timecounter;
    Boolean timelimit = true;
    long mLastClickTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent date_intent = new Intent(getApplicationContext(),date_managing.class);
        startService(date_intent);
        final MySQliteHandler handler = MySQliteHandler.open(getApplicationContext());
        editText = (EditText) findViewById(R.id.editText); // 이름
        editText2 = (EditText) findViewById(R.id.editText2); // 전화번호
        editText3 = (EditText) findViewById(R.id.editText3); // 생년월일
        editText4 =(EditText) findViewById(R.id.password); // 비번
        editText5 = (EditText) findViewById(R.id.password2); // 비번확인
        editText6 = (EditText) findViewById(R.id.set_provingnumber); //인증번호
        radioButton = (RadioButton) findViewById(R.id.radioButton); //남성
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);//여성
        Button button = (Button) findViewById(R.id.button);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        Button sendmessage = (Button) findViewById(R.id.send_message);

        sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                String phone = editText2.getText().toString();

                if(phone.isEmpty()==false&&timelimit){
                timecounter = (TextView) findViewById(R.id.timecounting);
                new CountDownTimer(60000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        timelimit = false;
                        timecounter.setVisibility(View.VISIBLE);
                        String strColor = "#FFD86F6F";
                        timecounter.setTextColor(Color.parseColor(strColor));
                        timecounter.setText("" + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        timecounter.setVisibility(View.GONE);
                        timelimit = true;
                    }
                }.start();
                Toast.makeText(getApplicationContext(),"인증 메세지를 전송했습니다",Toast.LENGTH_SHORT).show();
                proving_number = numberGen(4,1);
                JSONObject bodyJson = new JSONObject();
                JSONObject toJson = new JSONObject();
                JSONArray toArr = new JSONArray();

                try {
                    toJson.put("subject","짐박스 회원인증");
                    toJson.put("content","<짐박스 회원인증입니다> \n 인증번호는 "+proving_number+"입니다");				// 메시지 내용 * Type별로 최대 byte 제한이 다릅니다.* SMS: 80byte / LMS: 2000byte
                    toJson.put("to",editText2.getText().toString());					// 수신번호 목록  * 최대 50개까지 한번에 전송할 수 있습니다.
                    toArr.put(toJson);
                    bodyJson.put("type","SMS");				// 메시지 Type (sms | lms)
                    bodyJson.put("contentType","COMM");			// 메시지 내용 Type (AD | COMM) * AD: 광고용, COMM: 일반용 (default: COMM) * 광고용 메시지 발송 시 불법 스팸 방지를 위한 정보통신망법 (제 50조)가 적용됩니다.
                    bodyJson.put("countryCode","82");			// 국가 전화번호
                    bodyJson.put("from","01030430374");				// 발신번호 * 사전에 인증/등록된 번호만 사용할 수 있습니다.
                    bodyJson.put("subject","짐박스 회원인증");				// 메시지 제목 * LMS Type에서만 사용할 수 있습니다.
                    bodyJson.put("content","<짐박스 회원인증입니다> \n 인증번호는 "+proving_number+"입니다");				// 메시지 내용 * Type별로 최대 byte 제한이 다릅니다.* SMS: 80byte / LMS: 2000byte
                    bodyJson.put("messages", toArr);// 메시지 제목 * LMS Type에서만 사용할 수 있습니다.
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String body = bodyJson.toString();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://sens.apigw.ntruss.com")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .build();
                sendmessageService sendmessageService = retrofit.create(com.example.database_listview.sendmessageService.class);
                HashMap<String, String> headermap = new HashMap<>();

                try {
                    headermap.put("Content-Type", "application/json");
                    headermap.put("x-ncp-iam-access-key", "CooQR75mkuhD2PXv52ej");
                    headermap.put("x-ncp-apigw-timestamp", Timestamp);
                    headermap.put("x-ncp-apigw-signature-v2", makeSignature2());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                }
                Call<String> post = sendmessageService.sendmessage(headermap, body);
                post.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            Log.d("성공", "메세지 전송 성공.");
                        }
                        Log.d("네트워크 통신기록",response.toString());

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        t.printStackTrace();
                        Log.e("실패", "메세지 전송 실패");

                    }
                });
                }else if(timelimit==false){
                    Toast.makeText(getApplicationContext(),"인증번호가 이미 발송되었습니다. 인증번호는 1분간 유효합니다",Toast.LENGTH_SHORT).show();

                } else{
                    Toast.makeText(getApplicationContext(),"전화번호를 먼저 입력해주세요",Toast.LENGTH_SHORT).show();
                }}
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                String name = editText.getText().toString();
                String phonenumber = editText2.getText().toString();
                String agestr = editText3.getText().toString();
                String password = editText4.getText().toString();
                String password2 = editText5.getText().toString();
                String provingword = editText6.getText().toString();
                int age = 0;
                String gender = "";
                Boolean pass = false;
                if(radioButton.isChecked()){
                    gender = "남";

                } else if(radioButton2.isChecked()){
                    gender = "여";

                }
                if(name.equals("") || phonenumber.equals("") || agestr.equals("") || gender.equals("")||password.equals("")||password2.equals("")||provingword.equals("")){
                    pass = false;
                    Toast.makeText(getApplicationContext(),"양식을 다 채워주세요",Toast.LENGTH_SHORT).show();
                }else if(password.length()!=4){
                    pass=false;
                    Toast.makeText(getApplicationContext(),"비밀번호는 4자리 숫자를 입력해주세요",Toast.LENGTH_SHORT).show();
                }
                else if(!password.equals(password2)){
                    pass=false;
                    Toast.makeText(getApplicationContext(),"비밀번호와 비밀번호 확인이 일치하지 않습니다.",Toast.LENGTH_SHORT).show();
                }else if(!proving_number.equals(provingword)){
                    pass=false;
                    Toast.makeText(getApplicationContext(),"인증번호를 확인해주세요",Toast.LENGTH_SHORT).show();
                }else if(agestr.length()!=6){
                    pass=false;
                    Toast.makeText(getApplicationContext(),"생년월일을 정확히 입력해주세요",Toast.LENGTH_SHORT).show();
                }
                else{
                    age = Integer.parseInt(agestr);
                    pass =true;
                }

                if(pass){
                    handler.insert(name, phonenumber , age , gender);
                    Intent intent = new Intent(getApplicationContext(),choose_membership.class);
                    intent.putExtra("name",name);
                    startActivity(intent);
                }
            }

        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Intent intent = new Intent(getApplicationContext(),member_showing.class);
                startActivity(intent);
            }
        });

    }


    public static String numberGen(int len, int dupCd ) {

        Random rand = new Random();
        String numStr = ""; //난수가 저장될 변수

        for(int i=0;i<len;i++) {

            //0~9 까지 난수 생성
            String ran = Integer.toString(rand.nextInt(10));

            if(dupCd==1) {
                //중복 허용시 numStr에 append
                numStr += ran;
            }else if(dupCd==2) {
                //중복을 허용하지 않을시 중복된 값이 있는지 검사한다
                if(!numStr.contains(ran)) {
                    //중복된 값이 없으면 numStr에 append
                    numStr += ran;
                }else {
                    //생성된 난수가 중복되면 루틴을 다시 실행한다
                    i-=1;
                }
            }
        }
        return numStr;
    }

    public String makeSignature2() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

        String space = " ";					// one space
        String newLine = "\n";					// new line
        String method = "POST";					// method
        String url = "/sms/v2/services/ncp:sms:kr:256427198912:test_ver1/messages";	// url (include query string)
        String timestamp = Timestamp;		// current timestamp (epoch)
        String accessKey = "CooQR75mkuhD2PXv52ej";			// access key id (from portal or sub account)
        String secretKey = "XbCNhPP5MCNSL4fn5hBDPSMf8Q6IxSVrL2ewzn0t";

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeToString(rawHmac, Base64.NO_WRAP);
        return encodeBase64String;
    }






}
