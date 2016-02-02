package com.iranstco.stco;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class MyAdapter extends ArrayAdapter<String> {

    private final Activity  context;
    private final String[]  name;
    private final String[]  price;
    private final Integer[] imgid;


    public MyAdapter(Activity context, String[] name, String[] price, Integer[] imgid) {
        super(context, R.layout.row, name);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.name = name;
        this.price = price;
        this.imgid = imgid;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.row, null, true);
        TextView name1 = (TextView) rowView.findViewById(R.id.name1);
        TextView price1 = (TextView) rowView.findViewById(R.id.price1);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.ImageView01);
        price1.setText(price[position]);
        imageView.setImageResource(imgid[position]);
        name1.setText(name[position]);
        return rowView;
    };
}