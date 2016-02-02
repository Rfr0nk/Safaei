package com.iranstco.stco;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class shop extends ActionBarActivity {

    private MyDatabase          MyDataBase;
    public static Context       context;
    private ProgressDialog      pDialog;
    // JSON parser class
    JSONParser                  jsonParser  = new JSONParser();
    private static final String LOGIN_URL   = "http://entworkspace.ir/androidlogin/submit.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    String                      user;
    String                      code;


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
        setContentView(R.layout.main6);
        context = this.getApplicationContext();

        MyDataBase = new MyDatabase(context);
        SQLiteDatabase mydb = MyDataBase.getReadableDatabase();
        Cursor cursor2 = mydb.rawQuery("SELECT * FROM shop;", null);
        if (cursor2.moveToFirst())
        {
            do
            {
                String name1 = cursor2.getString(cursor2.getColumnIndex("name"));
                name.add(name1);
                String price1 = cursor2.getString(cursor2.getColumnIndex("price"));
                price.add(price1);

            }
            while (cursor2.moveToNext());
        }
        String[] name2 = new String[name.size()];
        name.toArray(name2);
        String[] price2 = new String[price.size()];
        price.toArray(price2);
        MyAdapter adapter = new MyAdapter(this, name2, price2, imgid);
        ListView lv = (ListView) findViewById(R.id.list6);
        lv.setAdapter(adapter);
        final int count = lv.getAdapter().getCount();

        Button finalbuy = (Button) findViewById(R.id.finalbuy);
        finalbuy.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                user = ((MyApplication) getApplicationContext()).getuser();
                for (int i1 = 0; i1 < count; i1++) {
                    int i2 = i1 + 1;

                    SQLiteDatabase mydb = MyDataBase.getReadableDatabase();
                    Cursor cursor2 = mydb.rawQuery("SELECT * FROM shop WHERE id=" + i2, null);
                    if (cursor2.moveToFirst())
                    {
                        do
                        {

                            code = cursor2.getString(cursor2.getColumnIndex("code"));

                        }
                        while (cursor2.moveToNext());
                    }

                    new AttemptLogin().execute();

                }

            }
        });

    }


    class AttemptLogin extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        boolean failure = false;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // here Check for success tag
            int success;
            try {
                Bundle extras = getIntent().getExtras();

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("user", user));
                params.add(new BasicNameValuePair("orderid", code));

                Log.d("request!", "starting");

                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);

                // checking  log for json response
                Log.d("Login attempt", json.toString());

                // success tag for json
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Successfully Login!", json.toString());

                    return json.getString(TAG_MESSAGE);
                } else {

                    return json.getString(TAG_MESSAGE);

                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        /**
         * Once the background process is done we need to Dismiss the progress dialog asap
         * **/
        @Override
        protected void onPostExecute(String message) {

            if (message != null) {
                Toast.makeText(shop.this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

}