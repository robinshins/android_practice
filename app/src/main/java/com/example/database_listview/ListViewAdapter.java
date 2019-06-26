package com.example.database_listview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> listViewItemList =  new ArrayList<ListViewItem>();
    public ListViewAdapter(){

    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(String month, String price, String plusinfo) {
        ListViewItem item = new ListViewItem();
        item.setMonth(month);
        item.setPrice(price);
        item.setPlusinfo(plusinfo);
        listViewItemList.add(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        TextView month = (TextView) convertView.findViewById(R.id.textView8) ;
        TextView price= (TextView) convertView.findViewById(R.id.textView9) ;
        TextView plusinfo= (TextView) convertView.findViewById(R.id.textView10) ;
        ListViewItem listViewItem = listViewItemList.get(position);
        month.setText(listViewItem.getMonth());
        price.setText(listViewItem.getPrice());
        plusinfo.setText(listViewItem.getPlusinfo());
        return convertView;
    }
}
