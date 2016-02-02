package com.iranstco.stco;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class subcategory extends ActionBarActivity {

    private MyDatabase      MyDataBase;
    public static Context   context;
    ListView                lv;
    ArrayList               data;
    HashMap<String, String> queryValues;
    int                     id;


    public class MyDatabase extends SQLiteAssetHelper {

        private static final String DATABASE_NAME    = "database.db";
        private static final int    DATABASE_VERSION = 1;


        public MyDatabase(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            setForcedUpgrade();
        }
    }


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
        setContentView(R.layout.subcat);
        context = this.getApplicationContext();
        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");
        ListView subcatlist = (ListView) findViewById(R.id.subcategory);
        if (id == 2) {
            String[] subcat = new String[]{ "زیر بخش اول", "زیر بخش دوم", "زیر بخش سوم" };
            ArrayAdapter<String> subcatategory = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, subcat);
            subcatlist.setAdapter(subcatategory);
            subcatlist.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    Intent intent = new Intent(subcategory.this, products.class);
                    int category = (int) ((id * 10) + arg3);
                    intent.putExtra("scategory", category);
                    startActivity(intent);

                }
            });
        }
    }

    // getters and setters...  
}
