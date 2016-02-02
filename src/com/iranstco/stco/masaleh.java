package com.iranstco.stco;

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


public class masaleh extends ActionBarActivity {

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

    String[]  name  = {
                    "سیمان مشهد",
                    "سیمان جوین",
                    "سیمان سبزوار ",
                    "سیمان قاین",
                    "گچ زیر کار خراسان",
                    "گچ زیرکار نمونه رضا ",
                    "گچ زیر کار خوشکار رضا",
                    "گچ سفید خراسان ",
                    " گچ سفید مشهد ",
                    " گچ سفید صالح سمنان ",
                    " گچ سفید رضا",
                    " پوکه معدنی ",
                    " پودر سنگ مشهد ",
                    " آجر 10 سوراخ مشهد ",
                    " بتن عیار 300 مشهد ",
                    " بتن عیار 300 بیرجند ",
                    " آجر 10 سوراخ قومنجان ",
                    " هبلکس بیرجند ",
                    " تیغه 8 گرگان ",
                    " تیغه 9 گرگان ",
                    " تیغه 10 گرگان ",
                    " تیغه 13 گرگان ",
                    " تیغه 15 گرگان "
                    };

    Integer[] imgid = {
                    R.drawable.c1,
                    R.drawable.c2,
                    R.drawable.c3,
                    R.drawable.c4,
                    R.drawable.c5,
                    R.drawable.c7,
                    R.drawable.c7,
                    R.drawable.c5,
                    R.drawable.c6,
                    R.drawable.c8,
                    R.drawable.c7,
                    R.drawable.c50,
                    R.drawable.c60,
                    R.drawable.c10,
                    R.drawable.c70,
                    R.drawable.c70,
                    R.drawable.c10,
                    R.drawable.c11,
                    R.drawable.c9,
                    R.drawable.c9,
                    R.drawable.c9,
                    R.drawable.c9,
                    R.drawable.c9,
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
        setContentView(R.layout.main2);
        context = this.getApplicationContext();

        MyDataBase = new MyDatabase(context);
        SQLiteDatabase mydb = MyDataBase.getReadableDatabase();
        String a10m = null;

        String a10q = null;

        String hlb = null;

        String s8g = null;

        String s9g = null;

        String s10g = null;

        String s13g = null;

        String s15g = null;

        String pm = null;

        String psm = null;

        String b300m = null;

        String b300b = null;

        String spm = null;

        String spj = null;

        String sps = null;

        String spq = null;

        String gsk = null;

        String gsm = null;

        String gss = null;

        String gsr = null;

        String gzk = null;

        String gnr = null;

        String gkr = null;

        Cursor cursor2 = mydb.rawQuery("SELECT * FROM masaleh ORDER BY id DESC LIMIT 1;", null);
        if (cursor2.moveToFirst())
        {
            do
            {
                a10m = cursor2.getString(cursor2.getColumnIndex("a10m"));

                a10q = cursor2.getString(cursor2.getColumnIndex("a10q"));

                hlb = cursor2.getString(cursor2.getColumnIndex("hlb"));

                s8g = cursor2.getString(cursor2.getColumnIndex("s8g"));

                s9g = cursor2.getString(cursor2.getColumnIndex("s9g"));

                s10g = cursor2.getString(cursor2.getColumnIndex("s10g"));

                s13g = cursor2.getString(cursor2.getColumnIndex("s13g"));

                s15g = cursor2.getString(cursor2.getColumnIndex("s15g"));

                pm = cursor2.getString(cursor2.getColumnIndex("pm"));

                psm = cursor2.getString(cursor2.getColumnIndex("psm"));

                b300m = cursor2.getString(cursor2.getColumnIndex("b300m"));

                b300b = cursor2.getString(cursor2.getColumnIndex("b300b"));

                spm = cursor2.getString(cursor2.getColumnIndex("spm"));

                spj = cursor2.getString(cursor2.getColumnIndex("spj"));

                sps = cursor2.getString(cursor2.getColumnIndex("sps"));

                spq = cursor2.getString(cursor2.getColumnIndex("spq"));

                gsk = cursor2.getString(cursor2.getColumnIndex("gsk"));

                gsm = cursor2.getString(cursor2.getColumnIndex("gsm"));

                gss = cursor2.getString(cursor2.getColumnIndex("gss"));

                gsr = cursor2.getString(cursor2.getColumnIndex("gsr"));

                gzk = cursor2.getString(cursor2.getColumnIndex("gzk"));

                gnr = cursor2.getString(cursor2.getColumnIndex("gnr"));

                gkr = cursor2.getString(cursor2.getColumnIndex("gkr"));

            }
            while (cursor2.moveToNext());
        }
        String[] price = {
                spm
                , spj
                , sps
                , spq
                , gzk
                , gnr
                , gkr
                , gsk
                , gsm
                , gss
                , gsr
                , pm
                , psm
                , a10m
                , b300m
                , b300b
                , a10q
                , hlb
                , s8g
                , s9g
                , s10g
                , s13g
                , s15g

        };
        MyAdapter adapter = new MyAdapter(this, name, price, imgid);
        ListView lv3 = (ListView) findViewById(R.id.list2);
        lv3.setAdapter(adapter);
    }

}