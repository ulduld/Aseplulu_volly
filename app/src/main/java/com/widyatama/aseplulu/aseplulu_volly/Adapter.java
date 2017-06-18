package com.widyatama.aseplulu.aseplulu_volly;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Asep Lulu A H on 18/06/2017.
 */

public class Adapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Activity activity;
    private List<Item> items;
    public Adapter(Activity activity,List<Item> items){
        this.activity=activity;
        this.items=items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null){
            inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView ==null){
            convertView=inflater.inflate(R.layout.custom_layout,null);
        }


        TextView name= (TextView) convertView.findViewById(R.id.name);
        TextView username= (TextView) convertView.findViewById(R.id.username);
        TextView email= (TextView) convertView.findViewById(R.id.email);
        TextView website= (TextView) convertView.findViewById(R.id.website);
        TextView phone= (TextView) convertView.findViewById(R.id.phone);
        //getting data for row
        Item item=items.get(position);


        name.setText("Name: " + item.getName());
        username.setText("User Name: " + String.valueOf(item.getUsername()));
        email.setText("email: " + String.valueOf(item.getEmail()));
        //  String genreStr="";
        // for(String str: item.getCompany()){
        //    genreStr +=str + ",";
        // }
        // genreStr = genreStr.length() >0 ? genreStr.substring(0, genreStr.length() - 2) : genreStr;
        website.setText("Website: " + String.valueOf(item.getWebsite()));
        phone.setText("Phone: " + String.valueOf(item.getPhone()));



        return convertView;
    }
}
