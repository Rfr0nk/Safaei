package com.iranstco.stco;

import android.app.Application;


public class MyApplication extends Application {

    private String user;


    public String getuser() {
        return user;
    }


    public void setuser(String user) {
        this.user = user;
    }

}