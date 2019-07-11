package com.example.database_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class showmemberAdapter extends BaseAdapter {

    private ArrayList<membersItem> listviewItemList = new ArrayList<membersItem>();

    public showmemberAdapter() {

    }

    @Override
    public int getCount() {
        return listviewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listviewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(String name, String gender, String membership, String availabledate, String phonenumber) {
        membersItem item = new membersItem();
        item.setMonth(membership);
        item.setGender(gender);
        item.setAvailabledate(availabledate);
        item.setName(name);
        item.setPhonenumber(phonenumber);
        listviewItemList.add(item);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.member, parent, false);}
            TextView name = (TextView) convertView.findViewById(R.id.textView20);
            TextView gender = (TextView) convertView.findViewById(R.id.textView21);
            TextView membership = (TextView) convertView.findViewById(R.id.textView22);
            TextView available_date = (TextView) convertView.findViewById(R.id.textView23);
            TextView phonenumber = (TextView) convertView.findViewById(R.id.textView24);
            membersItem membersItem = listviewItemList.get(position);
            name.setText(membersItem.getName());
            gender.setText(membersItem.getGender());
            membership.setText(membersItem.getMonth());
            available_date.setText(membersItem.getAvailabledate());
            phonenumber.setText(membersItem.getPhonenumber());
            return convertView;

    }
}
