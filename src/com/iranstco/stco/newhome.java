package com.iranstco.stco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.SaveCallback;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class newhome extends ActionBarActivity {

    List<String>            name         = new ArrayList<String>();
    List<String>            price        = new ArrayList<String>();
    List<String>            image        = new ArrayList<String>();
    List<String>            detail       = new ArrayList<String>();
    HashMap<String, String> hashMap      = new HashMap<String, String>();
    GridView                gv;
    GridView                gv1;
    GridView                gv2;
    GridView                gv3;
    ArrayList               prgmName;
    public static String[]  prgmNameList = { "پزشکی و سلامت", "دکوراسیون", "دکوراسیون داخلی", "رستوران", "زیبایی", "مد و پوشاک" };
    public static int[]     prgmImages   = { R.drawable.pezeshki, R.drawable.decor, R.drawable.decordakheli, R.drawable.resturant, R.drawable.zibaei, R.drawable.modepooshak };
    private MyDatabase      MyDataBase;
    public static Context   context;


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
        context = this.getApplicationContext();
        MyDataBase = new MyDatabase(context);
        SQLiteDatabase mydb = MyDataBase.getWritableDatabase();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(view, new ActionBar.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(R.layout.newhome);

        /** Get New Data **/
        AsyncHttpClient client = new AsyncHttpClient();
        // Http Request Params Object
        RequestParams params = new RequestParams();

        // Make Http call to getusers.php
        client.post("http://iranstco.com/android/dar.php", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(String response) {
                // Hide ProgressBar
                // Update SQLite DB with response sent by getusers.php
                updateSQLite(response);
            }


            // When error occured
            @Override
            public void onFailure(int statusCode, Throwable error, String content) {
                // TODO Auto-generated method stub
                // Hide ProgressBar
                if (statusCode == 404) {
                    Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                } else if (statusCode == 500) {
                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]",
                            Toast.LENGTH_LONG).show();
                }
            }


            public void updateSQLite(String response) {
                SQLiteDatabase mydb = MyDataBase.getWritableDatabase();
                ArrayList<HashMap<String, String>> usersynclist;
                usersynclist = new ArrayList<HashMap<String, String>>();
                // Create GSON object
                Gson gson = new GsonBuilder().create();

                try {
                    // Extract JSON array from the response
                    JSONArray arr = new JSONArray(response);
                    System.out.println(arr.length());
                    // If no of array elements is not zero
                    mydb.execSQL("DELETE FROM product");
                    if (arr.length() != 0) {
                        // Loop through each array element, get JSON object which has userid and username
                        for (int i = 0; i < arr.length(); i++) {
                            // Get JSON object

                            JSONObject obj = (JSONObject) arr.get(i);
                            String id = (String) obj.get("id");
                            String name1 = (String) obj.get("name");
                            String price1 = (String) obj.get("price");
                            String image1 = (String) obj.get("image1");
                            String detail1 = (String) obj.get("detail");
                            String category1 = (String) obj.get("category");
                            String description1 = (String) obj.get("description");
                            mydb.execSQL("INSERT INTO product (name, price, detail, image, category, description) VALUES('" + name1 + "','" + price1 + "','" + detail1 + "','" + image1 + "','" + category1 + "','" + description1 + "')");

                        }

                    }

                }
                catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });
        /** End Data Getting **/
        SliderLayout sliderShow = (SliderLayout) findViewById(R.id.slider5);
        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://iranstco.com/androidimage/21.PNG");
        url_maps.put("Big Bang Theory", "http://iranstco.com/androidimage/22.PNG");
        url_maps.put("House of Cards", "http://iranstco.com/androidimage/23.PNG");
        url_maps.put("Game of Thrones", "http://iranstco.com/androidimage/24.PNG");
        for (String name: url_maps.keySet()) {
            DefaultSliderView textSliderView = new DefaultSliderView(this);
            textSliderView
                    .image(url_maps.get(name));

            sliderShow.addSlider(textSliderView);
        }
        gv = (GridView) findViewById(R.id.grid1);
        gv.setAdapter(new CustomAdapter(this, prgmNameList, prgmImages));
        gv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                int ids = (int) arg3;
                if (ids == 0) {
                    Intent intent = new Intent(newhome.this, nosubcat.class);
                    ids = ids + 1;
                    intent.putExtra("id", ids);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(newhome.this, subcategory.class);
                    intent.putExtra("id", ids + 1);
                    startActivity(intent);
                }
            }
        });
    }


    public void saveUserProfile(View view) {

        ParseInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {

            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast toast = Toast.makeText(getApplicationContext(), "successed", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    e.printStackTrace();

                    Toast toast = Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

}