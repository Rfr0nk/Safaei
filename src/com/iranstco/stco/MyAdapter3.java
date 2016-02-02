package com.iranstco.stco;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;


public class MyAdapter3 extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] name;
    private final String[] price;
    private final String[] detail;
    private final String[] imgid;


    public MyAdapter3(Activity context, String[] name, String[] price, String[] detail, String[] imgid) {
        super(context, R.layout.row, name);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.name = name;
        this.price = price;
        this.detail = detail;
        this.imgid = imgid;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.row2, null, true);
        TextView name1 = (TextView) rowView.findViewById(R.id.name1);
        TextView detail1 = (TextView) rowView.findViewById(R.id.description);
        TextView price1 = (TextView) rowView.findViewById(R.id.price1);
        Typeface font = Typeface.createFromAsset(this.getContext().getAssets(), "bnazanin.ttf");
        name1.setTypeface(font);
        price1.setTypeface(font);
        detail1.setTypeface(font);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.ImageView01);
        price1.setText(price[position]);
        Picasso.with(context)
                .load(imgid[position]).fit()
                .into(imageView);
        name1.setText(name[position]);
        detail1.setText(detail[position]);
        return rowView;
    };
}