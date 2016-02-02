package com.iranstco.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iranstco.stco.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class listitem extends ActionBarActivity {

    public static Context   context;

    List<String>            name    = new ArrayList<String>();
    List<String>            price   = new ArrayList<String>();
    List<String>            image   = new ArrayList<String>();
    List<String>            detail  = new ArrayList<String>();
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
        setContentView(R.layout.list10);
        context = this.getApplicationContext();
        /** Get Data From Net **/
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
                            String image1 = (String) obj.get("image1");
                            String detail1 = (String) obj.get("detail");

                            name.add(name1);
                            price.add(price1);
                            image.add(image1);
                            detail.add(detail1);
                            hashMap.put(name1, id);

                        }
                        String[] name2 = new String[name.size()];
                        name.toArray(name2);
                        String[] price2 = new String[price.size()];
                        price.toArray(price2);
                        String[] imgid = new String[image.size()];
                        image.toArray(imgid);
                        String[] detail2 = new String[detail.size()];
                        detail.toArray(detail2);
                        MyAdapter2 adapter = new MyAdapter2(listitem.this, name2, price2, detail2, imgid);
                        ListView lv = (ListView) findViewById(R.id.list10);
                        lv.setAdapter(adapter);
                        lv.setOnItemClickListener(new OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> lv, View view, int position, long arg3) {
                                String selectedname = ((TextView) (view.findViewById(R.id.name1))).getText().toString();
                                Intent intent = new Intent(listitem.this, viewdetail.class);
                                int ids = Integer.parseInt(hashMap.get(selectedname));
                                intent.putExtra("id", ids);
                                startActivity(intent);
                            }
                        });

                    }

                }
                catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });
        /** End Data Get **/
    }

}