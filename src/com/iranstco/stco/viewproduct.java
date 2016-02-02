package com.iranstco.stco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.iranstco.shop.ExpandableTextView;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class viewproduct extends ActionBarActivity {

    private MyDatabase    MyDataBase;
    public static Context context;


    public class MyDatabase extends SQLiteAssetHelper {

        private static final String DATABASE_NAME    = "database.db";
        private static final int    DATABASE_VERSION = 1;


        public MyDatabase(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            setForcedUpgrade();
        }
    }

    List<String>            name    = new ArrayList<String>();
    List<String>            price   = new ArrayList<String>();
    List<String>            image   = new ArrayList<String>();
    HashMap<String, String> hashMap = new HashMap<String, String>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.action_bar, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(view, new ActionBar.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(R.layout.viewproduct);
        context = this.getApplicationContext();
        Typeface font = Typeface.createFromAsset(context.getAssets(), "bnazanin.ttf");
        TextView txtproductname = (TextView) findViewById(R.id.txtproductname);
        txtproductname.setTypeface(font);
        TextView txtprice = (TextView) findViewById(R.id.txtprice);
        txtprice.setTypeface(font);
        ExpandableTextView txtdescription = (ExpandableTextView) findViewById(R.id.txtdescription);
        txtdescription.setTypeface(font);
        Bundle extras = getIntent().getExtras();
        int ids = extras.getInt("id");
        String id1 = Integer.toString(ids);
        String name1 = null;
        String price1 = null;
        String description = null;
        Toast.makeText(getApplicationContext(), id1, Toast.LENGTH_SHORT).show();
        MyDataBase = new MyDatabase(context);
        SQLiteDatabase mydb = MyDataBase.getReadableDatabase();
        Cursor cursor3 = mydb.rawQuery("SELECT * FROM product WHERE id='" + ids + "'" +
                ";", null);
        if (cursor3.moveToFirst())
        {
            do
            {
                name1 = cursor3.getString(cursor3.getColumnIndex("name"));
                price1 = cursor3.getString(cursor3.getColumnIndex("price"));
                description = cursor3.getString(cursor3.getColumnIndex("description"));

            }
            while (cursor3.moveToNext());
        }
        txtproductname.setText(name1);
        txtprice.setText(price1);
        txtdescription.setText(description);
        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal", R.drawable.no_image);
        SliderLayout mDemoSlider = (SliderLayout) findViewById(R.id.slider1);
        for (String name: file_maps.keySet()) {
            DefaultSliderView textSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name));

            mDemoSlider.addSlider(textSliderView);
        }
    }
}