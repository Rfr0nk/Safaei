package com.iranstco.stco;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class vije extends ActionBarActivity {

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

    List<String> name  = new ArrayList<String>();
    List<String> price = new ArrayList<String>();

    Integer[]    imgid = {
                       R.drawable.c5555,
                       R.drawable.c5555,
                       R.drawable.c5555,
                       R.drawable.c5555,
                       R.drawable.c5555,
                       R.drawable.c5555,
                       R.drawable.c5555,
                       R.drawable.c5555,
                       R.drawable.c5555,
                       R.drawable.c5555,
                       R.drawable.c5555,
                       R.drawable.c5555,
                       R.drawable.c5555,
                       R.drawable.c5555,
                       R.drawable.c5555,
                       R.drawable.c5555,
                       R.drawable.c5555,
                       R.drawable.c5555,

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
        String hdf = null;
        String malamine = null;
        String abs = null;
        String cnc = null;
        String chini = null;
        String torki = null;
        String hariq = null;
        String hariqfelez = null;
        String zedserqatmdf = null;
        MyDataBase = new MyDatabase(context);
        SQLiteDatabase mydb = MyDataBase.getReadableDatabase();
        Cursor cursor3 = mydb.rawQuery("SELECT * FROM vije ORDER BY id DESC LIMIT 18 " +
                ";", null);
        if (cursor3.moveToFirst())
        {
            do
            {
                String name1 = cursor3.getString(cursor3.getColumnIndex("name"));
                name.add(name1);
            }
            while (cursor3.moveToNext());
        }
        String[] name2 = new String[name.size()];
        name.toArray(name2);
        cursor3 = mydb.rawQuery("SELECT * FROM vije ORDER BY id DESC LIMIT 18 " +
                ";", null);
        if (cursor3.moveToFirst())
        {
            do
            {
                String price1 = cursor3.getString(cursor3.getColumnIndex("price"));
                price.add(price1);
            }
            while (cursor3.moveToNext());
        }
        String[] price2 = new String[price.size()];
        price.toArray(price2);

        MyAdapter adapter = new MyAdapter(this, name2, price2, imgid);
        ListView lv = (ListView) findViewById(R.id.list4);
        lv.setAdapter(adapter);
    }

}