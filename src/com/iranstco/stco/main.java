package com.iranstco.stco;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class main extends ActionBarActivity {

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
        setContentView(R.layout.asli);
        context = this.getApplicationContext();
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.action_bar, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(view, new ActionBar.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        MyDataBase = new MyDatabase(context);
        SQLiteDatabase mydb = MyDataBase.getWritableDatabase();
        ArrayList data = new ArrayList();
        ImageView imgv1 = (ImageView) findViewById(R.id.imgv1);
        imgv1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(main.this, daropanjareh.class);
                startActivity(intent);

            }
        });
        ImageView imgv2 = (ImageView) findViewById(R.id.imgv2);
        imgv2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(main.this, IranstcoActivity.class);
                startActivity(intent);

            }
        });
        ImageView vije = (ImageView) findViewById(R.id.vije);
        vije.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(main.this, vije.class);
                startActivity(intent);

            }
        });
        ImageView imgv5 = (ImageView) findViewById(R.id.imgv5);
        imgv5.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(main.this, contact.class);
                startActivity(intent);

            }
        });
        ImageView image1 = (ImageView) findViewById(R.id.image1);
        image1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(main.this, masaleh.class);
                startActivity(intent);

            }
        });
        ImageView elek = (ImageView) findViewById(R.id.elek);
        elek.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(main.this, shop.class);
                startActivity(intent);

            }
        });
        String s = ((MyApplication) this.getApplication()).getuser();
        Toast.makeText(getApplicationContext(), s,
                Toast.LENGTH_LONG).show();
        ImageView web = (ImageView) findViewById(R.id.web);
        web.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://iranstco.com/"));
                startActivity(browserIntent);
            }
        });
        ImageView exit = (ImageView) findViewById(R.id.exit);
        exit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                finish();
                System.exit(0);

            }
        });
        // Create AsycHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        // Http Request Params Object
        RequestParams params = new RequestParams();
        // Make Http call to getusers.php
        client.post("http://iranstco.com/android/getcontent.php", params, new AsyncHttpResponseHandler() {

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

                    if (arr.length() != 0) {
                        // Loop through each array element, get JSON object which has userid and username
                        for (int i = 0; i < arr.length(); i++) {
                            // Get JSON object

                            JSONObject obj = (JSONObject) arr.get(i);
                            int i1 = 55;
                            int j1 = 74;
                            for (int k = 0; k < 18; k++) {
                                String name = (String) obj.get("" + i1);
                                String price = (String) obj.get("" + j1);
                                mydb.execSQL("INSERT INTO vije (name, price)VALUES('" + name + "','" + price + "')");

                                j1++;
                                i1++;

                            }

                            String hdf = (String) obj.get("hdf");
                            String malamine = (String) obj.get("malamine");
                            String abs = (String) obj.get("abs");
                            String cnc = (String) obj.get("cnc");
                            String chini = (String) obj.get("chini");
                            String tork = (String) obj.get("tork");
                            String hariq = (String) obj.get("hariq");
                            String hariqfelez = (String) obj.get("hariqfelez");
                            String zedserqatmdf = (String) obj.get("zedserqatmdf");
                            String polimer90 = (String) obj.get("1");
                            String polimer110 = (String) obj.get("2");
                            String polimer63 = (String) obj.get("3");
                            String yazd90 = (String) obj.get("4");
                            String yazd110 = (String) obj.get("5");
                            String yazd63 = (String) obj.get("6");
                            String tak90 = (String) obj.get("7");
                            String tak110 = (String) obj.get("8");
                            String tak63 = (String) obj.get("9");
                            String new16 = (String) obj.get("10");
                            String new20 = (String) obj.get("11");
                            String new25 = (String) obj.get("12");
                            String sun16 = (String) obj.get("13");
                            String sun20 = (String) obj.get("14");
                            String sun25 = (String) obj.get("15");
                            String iso16 = (String) obj.get("16");
                            String iso20 = (String) obj.get("17");
                            String iso25 = (String) obj.get("18");
                            String a10m = (String) obj.get("a10m");
                            String a10q = (String) obj.get("a10q");
                            String hlb = (String) obj.get("hlb");
                            String s8g = (String) obj.get("s8g");
                            String s9g = (String) obj.get("s9g");
                            String s10g = (String) obj.get("s10g");
                            String s13g = (String) obj.get("s13g");
                            String s15g = (String) obj.get("s15g");
                            String pm = (String) obj.get("pm");
                            String psm = (String) obj.get("psm");
                            String b300m = (String) obj.get("b300m");
                            String b300b = (String) obj.get("b300b");
                            String spm = (String) obj.get("spm");
                            String spj = (String) obj.get("spj");
                            String sps = (String) obj.get("sps");
                            String spq = (String) obj.get("spq");
                            String gsk = (String) obj.get("gsk");
                            String gsm = (String) obj.get("gsm");
                            String gss = (String) obj.get("gss");
                            String gsr = (String) obj.get("gsr");
                            String gzk = (String) obj.get("gzk");
                            String gnr = (String) obj.get("gnr");
                            String gkr = (String) obj.get("gkr");

                            String tehran15 = (String) obj.get("42");

                            String mashhad15 = (String) obj.get("43");
                            String khorasan15 = (String) obj.get("44");
                            String akhgar = (String) obj.get("45");

                            String mashhad2 = (String) obj.get("46");
                            String Lmashhad25 = (String) obj.get("47");
                            String semnan2 = (String) obj.get("48");
                            String Lsemnan25 = (String) obj.get("49");
                            String qoti = (String) obj.get("50");
                            String khayam = (String) obj.get("51");
                            String deland = (String) obj.get("52");
                            String sabet = (String) obj.get("53");
                            String zaman = (String) obj.get("54");

                            mydb.execSQL("INSERT INTO daropanjareh (hdf, malamine, abs, cnc, chini, torki, hariq, hariqfelez, zedserqatmdf) VALUES('" + hdf + "','" + malamine + "','" + abs + "','" + cnc + "','" + chini + "','" + tork + "','" + hariq + "','" + hariqfelez + "','" + zedserqatmdf + "')");
                            mydb.execSQL("INSERT INTO elektriki (tehran15, khorasan15, akhgar, khayam, deland, sabet, zaman, mashhad2, Lmashhad25, semnan2, Lsemnan2, qoti) VALUES('" + tehran15 + "','" + khorasan15 + "','" + akhgar + "','" + khayam + "','" + deland + "','" + sabet + "','" + zaman + "','" + mashhad2 + "','" + Lmashhad25 + "','" + semnan2 + "','" + Lsemnan25 + "','" + qoti + "')");
                            mydb.execSQL("INSERT INTO mekaniki (polimer90, polimer110, polimer63, yazd90, yazd110, yazd63, tak90, tak110, tak63, new16, new20, new25, sun16, sun20, sun25, iso16, iso20, iso25) VALUES('" + polimer90 + "','" + polimer110 + "','" + polimer63 + "','" + yazd90 + "','" + yazd110 + "','" + yazd63 + "','" + tak90 + "','" + tak110 + "','" + tak63 + "','" + new16 + "','" + new20 + "','" + new25 + "','" + sun16 + "','" + sun20 + "','" + sun25 + "','" + iso16 + "','" + iso20 + "','" + iso25 + "')");
                            mydb.execSQL("INSERT INTO masaleh (a10m, a10q, hlb, s8g, s9g, s10g, s13g, s15g, pm, psm, b300m, b300b, spm, spj, sps, spq, gsk, gsm, gss, gsr, gzk, gnr, gkr) VALUES('" + a10m + "','" + a10q + "','" + hlb + "','" + s8g + "','" + s9g + "','" + s10g + "','" + s13g + "','" + s15g + "','" + pm + "','" + psm + "','" + b300m + "','" + b300b + "','" + spm + "','" + spj + "','" + sps + "','" + spq + "','" + gsk + "','" + gsm + "','" + gss + "','" + gsr + "','" + gzk + "','" + gnr + "','" + gkr + "')");
                        }

                    }

                }
                catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                mydb.close();
            }
        });
        ImageView imgv3 = (ImageView) findViewById(R.id.imgv3);
        imgv3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //update database
                AsyncHttpClient client = new AsyncHttpClient();
                // Http Request Params Object
                RequestParams params = new RequestParams();
                // Make Http call to getusers.php
                client.post("http://iranstco.com/android/getcontent.php", params, new AsyncHttpResponseHandler() {

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

                            if (arr.length() != 0) {
                                // Loop through each array element, get JSON object which has userid and username
                                for (int i = 0; i < arr.length(); i++) {
                                    // Get JSON object

                                    JSONObject obj = (JSONObject) arr.get(i);
                                    int i1 = 55;
                                    int j1 = 74;
                                    for (int k = 0; k < 18; k++) {
                                        String name = (String) obj.get("" + i1);
                                        String price = (String) obj.get("" + j1);
                                        mydb.execSQL("INSERT INTO vije (name, price)VALUES('" + name + "','" + price + "')");

                                        j1++;
                                        i1++;

                                    }

                                    String hdf = (String) obj.get("hdf");
                                    String malamine = (String) obj.get("malamine");
                                    String abs = (String) obj.get("abs");
                                    String cnc = (String) obj.get("cnc");
                                    String chini = (String) obj.get("chini");
                                    String tork = (String) obj.get("tork");
                                    String hariq = (String) obj.get("hariq");
                                    String hariqfelez = (String) obj.get("hariqfelez");
                                    String zedserqatmdf = (String) obj.get("zedserqatmdf");
                                    String polimer90 = (String) obj.get("1");
                                    String polimer110 = (String) obj.get("2");
                                    String polimer63 = (String) obj.get("3");
                                    String yazd90 = (String) obj.get("4");
                                    String yazd110 = (String) obj.get("5");
                                    String yazd63 = (String) obj.get("6");
                                    String tak90 = (String) obj.get("7");
                                    String tak110 = (String) obj.get("8");
                                    String tak63 = (String) obj.get("9");
                                    String new16 = (String) obj.get("10");
                                    String new20 = (String) obj.get("11");
                                    String new25 = (String) obj.get("12");
                                    String sun16 = (String) obj.get("13");
                                    String sun20 = (String) obj.get("14");
                                    String sun25 = (String) obj.get("15");
                                    String iso16 = (String) obj.get("16");
                                    String iso20 = (String) obj.get("17");
                                    String iso25 = (String) obj.get("18");
                                    String a10m = (String) obj.get("a10m");
                                    String a10q = (String) obj.get("a10q");
                                    String hlb = (String) obj.get("hlb");
                                    String s8g = (String) obj.get("s8g");
                                    String s9g = (String) obj.get("s9g");
                                    String s10g = (String) obj.get("s10g");
                                    String s13g = (String) obj.get("s13g");
                                    String s15g = (String) obj.get("s15g");
                                    String pm = (String) obj.get("pm");
                                    String psm = (String) obj.get("psm");
                                    String b300m = (String) obj.get("b300m");
                                    String b300b = (String) obj.get("b300b");
                                    String spm = (String) obj.get("spm");
                                    String spj = (String) obj.get("spj");
                                    String sps = (String) obj.get("sps");
                                    String spq = (String) obj.get("spq");
                                    String gsk = (String) obj.get("gsk");
                                    String gsm = (String) obj.get("gsm");
                                    String gss = (String) obj.get("gss");
                                    String gsr = (String) obj.get("gsr");
                                    String gzk = (String) obj.get("gzk");
                                    String gnr = (String) obj.get("gnr");
                                    String gkr = (String) obj.get("gkr");

                                    String tehran15 = (String) obj.get("42");

                                    String mashhad15 = (String) obj.get("43");
                                    String khorasan15 = (String) obj.get("44");
                                    String akhgar = (String) obj.get("45");

                                    String mashhad2 = (String) obj.get("46");
                                    String Lmashhad25 = (String) obj.get("47");
                                    String semnan2 = (String) obj.get("48");
                                    String Lsemnan25 = (String) obj.get("49");
                                    String qoti = (String) obj.get("50");
                                    String khayam = (String) obj.get("51");
                                    String deland = (String) obj.get("52");
                                    String sabet = (String) obj.get("53");
                                    String zaman = (String) obj.get("54");

                                    mydb.execSQL("INSERT INTO daropanjareh (hdf, malamine, abs, cnc, chini, torki, hariq, hariqfelez, zedserqatmdf) VALUES('" + hdf + "','" + malamine + "','" + abs + "','" + cnc + "','" + chini + "','" + tork + "','" + hariq + "','" + hariqfelez + "','" + zedserqatmdf + "')");
                                    mydb.execSQL("INSERT INTO elektriki (tehran15, khorasan15, akhgar, khayam, deland, sabet, zaman, mashhad2, Lmashhad25, semnan2, Lsemnan2, qoti) VALUES('" + tehran15 + "','" + khorasan15 + "','" + akhgar + "','" + khayam + "','" + deland + "','" + sabet + "','" + zaman + "','" + mashhad2 + "','" + Lmashhad25 + "','" + semnan2 + "','" + Lsemnan25 + "','" + qoti + "')");
                                    mydb.execSQL("INSERT INTO mekaniki (polimer90, polimer110, polimer63, yazd90, yazd110, yazd63, tak90, tak110, tak63, new16, new20, new25, sun16, sun20, sun25, iso16, iso20, iso25) VALUES('" + polimer90 + "','" + polimer110 + "','" + polimer63 + "','" + yazd90 + "','" + yazd110 + "','" + yazd63 + "','" + tak90 + "','" + tak110 + "','" + tak63 + "','" + new16 + "','" + new20 + "','" + new25 + "','" + sun16 + "','" + sun20 + "','" + sun25 + "','" + iso16 + "','" + iso20 + "','" + iso25 + "')");
                                    mydb.execSQL("INSERT INTO masaleh (a10m, a10q, hlb, s8g, s9g, s10g, s13g, s15g, pm, psm, b300m, b300b, spm, spj, sps, spq, gsk, gsm, gss, gsr, gzk, gnr, gkr) VALUES('" + a10m + "','" + a10q + "','" + hlb + "','" + s8g + "','" + s9g + "','" + s10g + "','" + s13g + "','" + s15g + "','" + pm + "','" + psm + "','" + b300m + "','" + b300b + "','" + spm + "','" + spj + "','" + sps + "','" + spq + "','" + gsk + "','" + gsm + "','" + gss + "','" + gsr + "','" + gzk + "','" + gnr + "','" + gkr + "')");
                                }

                            }

                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        mydb.close();
                    }
                });
                Toast.makeText(getApplicationContext(), "عملیات بروزرسانی با موفقیت انجام شد",
                        Toast.LENGTH_LONG).show();
                //update db end

            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private String inputstreamToString(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            return builder.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
