package com.iranstco.stco;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class daropanjareh extends ActionBarActivity {

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
                    "درجه یک HDF",
                    "ملامینه درجه یک",
                    "درجه یک ABC",
                    "درجه یک CNC",

                    "ضد سرقت چینی",
                    "ضد حریق چوبی با چهارچوب",
                    "ضد سرقت ترک",

                    "ضد حریق فلزی با چهارچوب",
                    "MDF ضد سرقت"
                    };

    Integer[] imgid = {
                    R.drawable.c34,
                    R.drawable.c35,
                    R.drawable.c37,
                    R.drawable.c36,

                    R.drawable.c31,
                    R.drawable.c30,
                    R.drawable.c32,

                    R.drawable.c29,
                    R.drawable.c33,
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
        setContentView(R.layout.main);
        context = this.getApplicationContext();

        MyDataBase = new MyDatabase(context);
        SQLiteDatabase mydb = MyDataBase.getReadableDatabase();
        String hdf = null;
        String malamine = null;
        String abs = null;
        String cnc = null;
        String chini = null;
        String torki = null;
        String hariq = null;
        String hariqfelez = null;
        String zedserqatmdf = null;
        Cursor cursor2 = mydb.rawQuery("SELECT * FROM daropanjareh ORDER BY id DESC LIMIT 1;", null);
        if (cursor2.moveToFirst())
        {
            do
            {
                hdf = cursor2.getString(cursor2.getColumnIndex("hdf"));
                malamine = cursor2.getString(cursor2.getColumnIndex("malamine"));
                abs = cursor2.getString(cursor2.getColumnIndex("abs"));
                cnc = cursor2.getString(cursor2.getColumnIndex("cnc"));
                chini = cursor2.getString(cursor2.getColumnIndex("chini"));
                hariq = cursor2.getString(cursor2.getColumnIndex("hariq"));
                torki = cursor2.getString(cursor2.getColumnIndex("torki"));
                hariqfelez = cursor2.getString(cursor2.getColumnIndex("hariqfelez"));
                zedserqatmdf = cursor2.getString(cursor2.getColumnIndex("zedserqatmdf"));
            }
            while (cursor2.moveToNext());
        }
        String[] price = {
                hdf,
                malamine,
                abs,
                cnc,
                chini,
                hariq,
                torki,
                hariqfelez,
                zedserqatmdf
        };
        MyAdapter adapter = new MyAdapter(this, name, price, imgid);
        ListView lv = (ListView) findViewById(R.id.list1);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> lv, View view, int position, long arg3) {

                Intent intent = new Intent(daropanjareh.this, item.class);
                int ids = (int) arg3;
                ids = ids + 1;
                Log.d("id", " " + ids);
                String title = "ame";
                intent.putExtra("title", title);
                intent.putExtra("id", ids);
                startActivity(intent);

            }
        });
    }

}