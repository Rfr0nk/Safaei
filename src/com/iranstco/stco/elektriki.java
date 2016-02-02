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


public class elektriki extends ActionBarActivity {

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
                    "سیم 1.5 و 2.5 سهند ",
                    "سیم 1.5 و 2.5 خراسان ",
                    "سیم 1.5 و 2.5 اخگر غرب",
                    " کلید و پریز جور برند دلند ",
                    " کلید و پریز جور برند ثابت الکتریک ",
                    " کلید و پریز جور برند زمان الکتریک ",
                    " قوطی کلید ",

                    " لوله 2 ونیکا مشهد ",
                    " لوله 2 نسوز سمنان ",
                    " لوله 2.5 ونیکا مشهد ",

                    " لوله 2.5 نسوز سمنان ",
                    "کلید و پریز جور برند خیام الکتریک "
                    };

    Integer[] imgid = {
                    R.drawable.c18,
                    R.drawable.c80,
                    R.drawable.c21,
                    R.drawable.c23,
                    R.drawable.c24,
                    R.drawable.c25,
                    R.drawable.c28,
                    R.drawable.c26,
                    R.drawable.c27,
                    R.drawable.c26,
                    R.drawable.c27,
                    R.drawable.c22
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
        setContentView(R.layout.main5);
        context = this.getApplicationContext();

        MyDataBase = new MyDatabase(context);
        SQLiteDatabase mydb = MyDataBase.getReadableDatabase();
        String tehran15 = null;

        String mashhad15 = null;

        String khorasan15 = null;

        String akhgar = null;

        String khayam = null;

        String deland = null;

        String sabet = null;

        String zaman = null;

        String mashhad2 = null;

        String Lmashhad25 = null;

        String semnan2 = null;

        String Lsemnan25 = null;

        String qoti = null;

        Cursor cursor2 = mydb.rawQuery("SELECT * FROM elektriki ORDER BY id DESC LIMIT 1;", null);
        if (cursor2.moveToFirst())
        {
            do
            {
                tehran15 = cursor2.getString(cursor2.getColumnIndex("tehran15"));

                khorasan15 = cursor2.getString(cursor2.getColumnIndex("khorasan15"));

                akhgar = cursor2.getString(cursor2.getColumnIndex("akhgar"));

                khayam = cursor2.getString(cursor2.getColumnIndex("khayam"));

                deland = cursor2.getString(cursor2.getColumnIndex("deland"));

                sabet = cursor2.getString(cursor2.getColumnIndex("sabet"));

                zaman = cursor2.getString(cursor2.getColumnIndex("zaman"));

                mashhad2 = cursor2.getString(cursor2.getColumnIndex("mashhad2"));

                Lmashhad25 = cursor2.getString(cursor2.getColumnIndex("Lmashhad25"));

                semnan2 = cursor2.getString(cursor2.getColumnIndex("semnan2"));

                Lsemnan25 = cursor2.getString(cursor2.getColumnIndex("Lsemnan2"));

                qoti = cursor2.getString(cursor2.getColumnIndex("qoti"));

            }
            while (cursor2.moveToNext());
        }
        String[] price = {
                tehran15,
                khorasan15,
                akhgar,
                khayam,
                deland,
                sabet,
                zaman,
                mashhad2,
                Lmashhad25,
                semnan2,
                Lsemnan25,
                qoti

        };
        MyAdapter adapter = new MyAdapter(this, name, price, imgid);
        ListView lv = (ListView) findViewById(R.id.list5);
        lv.setAdapter(adapter);
    }

}