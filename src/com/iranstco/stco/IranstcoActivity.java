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


public class IranstcoActivity extends ActionBarActivity {

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
                    "لوله 90 فشار قوی برند پلیمر گلپایگان",
                    "لوله 110 فشار قوی برند پلیمر گلپایگان",
                    "لوله 63 فشار قوی برند پلیمر گلپایگان",
                    "لوله 90 فشار قوی برند یزد پلیمر گلپایگان",
                    "لوله 110 فشار قوی برند یزد پلیمر گلپایگان",
                    "لوله 63 فشار قوی برند یزد پلیمر گلپایگان",
                    "لوله 90 فشار قوی برند تک ستاره گلپایگان",
                    "لوله 110 فشار قوی برند تک ستاره گلپایگان",
                    "لوله 63 فشار قوی برند تک ستاره گلپایگان",

                    "لوله 16 پنج لایه برند نیوپایپ",
                    "لوله 20 پنج لایه برند نیوپایپ",
                    "لوله 16 پنج لایه برندایزوپایپ",
                    "لوله 20 پنج لایه برندایزوپایپ",
                    "لوله 20 پنج لایه برند سان پایپ",
                    "لوله 16 پنج لایه برند سان پایپ",
                    "لوله 25 پنج لایه برند نیوپایپ",
                    "لوله 25 پنج لایه برند سان پایپ",
                    "لوله 25 پنج لایه برندایزوپایپ"
                    };

    Integer[] imgid = {
                    R.drawable.c122,
                    R.drawable.c122,
                    R.drawable.c122,
                    R.drawable.c133,
                    R.drawable.c133,
                    R.drawable.c133,
                    R.drawable.c144,
                    R.drawable.c144,
                    R.drawable.c144,
                    R.drawable.c15,
                    R.drawable.c15,
                    R.drawable.c16,
                    R.drawable.c16,
                    R.drawable.c17,
                    R.drawable.c17,
                    R.drawable.c16,
                    R.drawable.c17,
                    R.drawable.c15,
                    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.action_bar, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(view, new ActionBar.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(R.layout.main1);
        context = this.getApplicationContext();
        MyDataBase = new MyDatabase(context);
        SQLiteDatabase mydb = MyDataBase.getReadableDatabase();
        String poli90 = null;
        String poli110 = null;
        String poli63 = null;
        String yazd90 = null;
        String yazd110 = null;
        String yazd63 = null;
        String tak90 = null;
        String tak110 = null;
        String tak63 = null;
        String new16 = null;
        String new20 = null;
        String sun16 = null;
        String sun20 = null;
        String iso16 = null;
        String iso20 = null;
        String new25 = null;
        String sun25 = null;
        String iso25 = null;
        Cursor cursor2 = mydb.rawQuery("SELECT * FROM mekaniki ORDER BY id DESC LIMIT 1;", null);
        if (cursor2.moveToFirst())
        {
            do
            {
                poli90 = cursor2.getString(cursor2.getColumnIndex("polimer90"));
                poli110 = cursor2.getString(cursor2.getColumnIndex("polimer110"));
                poli63 = cursor2.getString(cursor2.getColumnIndex("polimer63"));
                yazd90 = cursor2.getString(cursor2.getColumnIndex("yazd90"));
                yazd110 = cursor2.getString(cursor2.getColumnIndex("yazd110"));
                yazd63 = cursor2.getString(cursor2.getColumnIndex("yazd63"));
                tak90 = cursor2.getString(cursor2.getColumnIndex("tak90"));
                tak110 = cursor2.getString(cursor2.getColumnIndex("tak110"));
                tak63 = cursor2.getString(cursor2.getColumnIndex("tak63"));
                new16 = cursor2.getString(cursor2.getColumnIndex("new16"));
                new20 = cursor2.getString(cursor2.getColumnIndex("new20"));
                sun16 = cursor2.getString(cursor2.getColumnIndex("sun16"));
                sun20 = cursor2.getString(cursor2.getColumnIndex("sun20"));
                iso16 = cursor2.getString(cursor2.getColumnIndex("iso16"));
                iso20 = cursor2.getString(cursor2.getColumnIndex("iso20"));
                sun25 = cursor2.getString(cursor2.getColumnIndex("sun25"));
                iso25 = cursor2.getString(cursor2.getColumnIndex("iso25"));
                new25 = cursor2.getString(cursor2.getColumnIndex("new25"));
            }
            while (cursor2.moveToNext());
        }
        String[] price = {
                poli90,
                poli110,
                poli63,
                yazd90,
                yazd110,
                yazd63,
                tak90,
                tak110,
                tak63,
                new16,
                new20,
                iso16,
                iso20,
                sun20,
                sun16,
                new25,
                sun25,
                iso25
        };

        MyAdapter adapter = new MyAdapter(this, name, price, imgid);
        ListView lv1 = (ListView) findViewById(R.id.listView1);
        lv1.setAdapter(adapter);
    }

}