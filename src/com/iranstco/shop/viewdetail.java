package com.iranstco.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iranstco.stco.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class viewdetail extends ActionBarActivity {

    public static Context   context;

    List<String>            name    = new ArrayList<String>();
    List<String>            price   = new ArrayList<String>();
    List<String>            image   = new ArrayList<String>();
    HashMap<String, String> hashMap = new HashMap<String, String>();


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
        setContentView(R.layout.viewdetail);
        context = this.getApplicationContext();
        Typeface font = Typeface.createFromAsset(context.getAssets(), "bnazanin.ttf");
        TextView txtproductname = (TextView) findViewById(R.id.txtproductname);
        txtproductname.setTypeface(font);
        TextView txtprice = (TextView) findViewById(R.id.txtprice);
        txtprice.setTypeface(font);
        ExpandableTextView txtdescription = (ExpandableTextView) findViewById(R.id.txtdescription);
        txtdescription.setTypeface(font);
        SliderLayout sliderShow = (SliderLayout) findViewById(R.id.slider1);
        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
        for (String name: url_maps.keySet()) {
            DefaultSliderView textSliderView = new DefaultSliderView(this);
            textSliderView
                    .image(url_maps.get(name));

            sliderShow.addSlider(textSliderView);
        }
        Bundle extras = getIntent().getExtras();
        int ids = extras.getInt("id");
        String id1 = Integer.toString(ids);
        AsyncHttpClient client = new AsyncHttpClient();
        // Http Request Params Object
        RequestParams params = new RequestParams();
        params.put("id", id1);
        // Make Http call to getusers.php
        client.get("http://iranstco.com/android/viewdetail.php", params, new AsyncHttpResponseHandler() {

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
                ArrayList<HashMap<String, String>> usersynclist;
                usersynclist = new ArrayList<HashMap<String, String>>();
                // Create GSON object
                Gson gson = new GsonBuilder().create();

                try {
                    // Extract JSON array from the response
                    JSONArray arr = new JSONArray(response);
                    System.out.println(arr.length());
                    // If no of array elements is not zero

                    if (arr.length() != 0) {
                        // Loop through each array element, get JSON object which has userid and username
                        for (int i = 0; i < arr.length(); i++) {
                            // Get JSON object

                            JSONObject obj = (JSONObject) arr.get(i);
                            String id = (String) obj.get("id");
                            String name1 = (String) obj.get("name");
                            String price1 = (String) obj.get("price");
                            String description = (String) obj.get("description");
                            String image1 = (String) obj.get("image1");
                            TextView txtprice = (TextView) findViewById(R.id.txtprice);
                            txtprice.setText(price1);
                            TextView txtproductname = (TextView) findViewById(R.id.txtproductname);
                            txtproductname.setText(name1);
                            ExpandableTextView txtdescription = (ExpandableTextView) findViewById(R.id.txtdescription);
                            txtdescription.setText(description);

                        }

                    }

                }
                catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

}