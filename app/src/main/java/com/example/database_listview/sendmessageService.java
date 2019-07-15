package com.example.database_listview;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface sendmessageService {
    @POST("/sms/v2/services/ncp:sms:kr:256427198912:test_ver1/messages")
    Call<String> sendmessage(@HeaderMap HashMap<String, String> map, @Body String ccc);

    @POST("v1/sms/services/ncp%3Asms%3Akr%3A256427198912%3Atest_ver1/messages")
    Call<String> sendversion1(@HeaderMap HashMap<String, String> map1, @Body String ccc1);

}
