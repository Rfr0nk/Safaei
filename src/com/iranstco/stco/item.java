package com.iranstco.stco;

import android.app.ProgressDialog;
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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class item extends ActionBarActivity {

    public static Context       context;
    private MyDatabase          MyDataBase;
    private ProgressDialog      pDialog;
    // JSON parser class
    JSONParser                  jsonParser  = new JSONParser();
    private static final String LOGIN_URL   = "http://entworkspace.ir/androidlogin/submit.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    String                      user;
    int                         id;
    String                      name;
    String                      price;
    String                      code;


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
        setContentView(R.layout.list);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.action_bar, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(view, new ActionBar.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        context = this.getApplicationContext();
        MyDataBase = new MyDatabase(context);
        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");
        TextView lists = (TextView) findViewById(R.id.lists);
        user = ((MyApplication) getApplicationContext()).getuser();
        lists.setText(String.valueOf(id));
        SQLiteDatabase mydb = MyDataBase.getWritableDatabase();
        Cursor cours = mydb.rawQuery("SELECT * FROM daropanjarehdetail WHERE id=" + id, null);
        if (cours.moveToFirst())
        {
            do
            {
                int ida = cours.getInt(cours.getColumnIndex("id"));
                Log.d("Clicked item ida", " " + ida);
                String str = cours.getString(cours.getColumnIndex("detail"));
                name = cours.getString(cours.getColumnIndex("name"));
                price = cours.getString(cours.getColumnIndex("price"));
                code = cours.getString(cours.getColumnIndex("code"));
                System.out.println(str);
                lists.setText(str);
            }
            while (cours.moveToNext());
        }
        cours.close();
        mydb.close();
        Button buy = (Button) findViewById(R.id.buy);
        buy.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                final String user = ((MyApplication) getApplicationContext()).getuser();
                if (user != null && !user.isEmpty()) {
                    SQLiteDatabase mydb = MyDataBase.getWritableDatabase();
                    mydb.execSQL("INSERT INTO shop (name, price, code) VALUES('" + name + "','" + price + "','" + code + "')");

                }
                else {
                    Intent intent = new Intent(item.this, login.class);
                    startActivity(intent);
                }

            }
        });

    }

}