package com.iranstco.stco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class products extends ActionBarActivity {

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
    List<String>            detail  = new ArrayList<String>();
    List<String>            imageid = new ArrayList<String>();
    HashMap<String, String> hashMap = new HashMap<String, String>();
    int                     category;

    Integer[]               imgid   = {
                                    R.drawable.c5,
                                    R.drawable.c6,
                                    R.drawable.c7,
                                    R.drawable.c8,
                                    };


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
        setContentView(R.layout.main4);
        context = this.getApplicationContext();
        Bundle extras = getIntent().getExtras();
        category = extras.getInt("scategory");
        MyDataBase = new MyDatabase(context);
        SQLiteDatabase mydb = MyDataBase.getReadableDatabase();
        Cursor cursor3 = mydb.rawQuery("SELECT * FROM product WHERE category='" + category + "'" +
                ";", null);
        if (cursor3.moveToFirst())
        {
            do
            {
                String id = cursor3.getString(cursor3.getColumnIndex("id"));
                String name1 = cursor3.getString(cursor3.getColumnIndex("name"));
                name.add(name1);
                String price1 = cursor3.getString(cursor3.getColumnIndex("price"));
                price.add(price1);
                String detail1 = cursor3.getString(cursor3.getColumnIndex("detail"));
                detail.add(detail1);
                String imageid1 = cursor3.getString(cursor3.getColumnIndex("image"));
                imageid.add(imageid1);
                hashMap.put(name1, id);

            }
            while (cursor3.moveToNext());
        }
        String[] name2 = new String[name.size()];
        name.toArray(name2);
        String[] price2 = new String[price.size()];
        price.toArray(price2);
        String[] detail2 = new String[detail.size()];
        detail.toArray(detail2);
        String[] imageid2 = new String[imageid.size()];
        imageid.toArray(imageid2);
        MyAdapter3 adapter = new MyAdapter3(products.this, name2, price2, detail2, imageid2);
        ListView lv = (ListView) findViewById(R.id.list4);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> lv, View view, int position, long arg3) {
                String selectedname = ((TextView) (view.findViewById(R.id.name1))).getText().toString();
                Intent intent = new Intent(products.this, viewproduct.class);
                int ids = Integer.parseInt(hashMap.get(selectedname));
                intent.putExtra("id", ids);
                startActivity(intent);
            }
        });
    }

}