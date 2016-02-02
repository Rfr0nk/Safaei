package com.iranstco.stco;

import android.app.Application;
import com.parse.Parse;
import com.parse.PushService;


public class parse extends Application {

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate() {
        Parse.initialize(this, "rgWHTfedn4ZxtgEKvXPu4agwjkaFPQ5U2pTpEVkZ", "m7yV7zHcWnDjti19thKBX8nwcnPXHFjINkJJHttj");
        PushService.setDefaultPushCallback(this, newhome.class);
    }
}
