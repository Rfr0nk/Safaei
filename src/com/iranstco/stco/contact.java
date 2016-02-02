package com.iranstco.stco;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class contact extends ActionBarActivity {

    private MyDatabase      MyDataBase;
    public static Context   context;
    ListView                lv;
    ArrayList               data;
    HashMap<String, String> queryValues;


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
        setContentView(R.layout.contact);
        context = this.getApplicationContext();
        MyDataBase = new MyDatabase(context);
        SQLiteDatabase mydb = MyDataBase.getWritableDatabase();

        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            // Create a new HttpClient and Post Header
                            HttpClient httpclient = new DefaultHttpClient();
                            EditText name = (EditText) findViewById(R.id.name);
                            String name1 = name.getText().toString();
                            EditText matn1 = (EditText) findViewById(R.id.matn);
                            String matn = matn1.getText().toString();
                            HttpPost httppost = new HttpPost("http://www.yoursite.com/registration.php");

                            try {
                                // Add your data
                                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                                nameValuePairs.add(new BasicNameValuePair("name1", name1));
                                nameValuePairs.add(new BasicNameValuePair("matn", matn));
                                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                                // Execute HTTP Post Request
                                HttpResponse response = httpclient.execute(httppost);
                                HttpEntity resEntity = response.getEntity();
                                String responseStr = EntityUtils.toString(resEntity).trim();
                                Toast.makeText(getApplicationContext(), responseStr,
                                        Toast.LENGTH_LONG).show();
                            }
                            catch (ClientProtocolException e) {
                                // TODO Auto-generated catch block
                            }
                            catch (IOException e) {
                                // TODO Auto-generated catch block
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                Toast.makeText(getApplicationContext(), "پیام با موفقیت ارسال شد",
                        Toast.LENGTH_LONG).show();

            }
        });
    }
    // getters and setters...  
}
