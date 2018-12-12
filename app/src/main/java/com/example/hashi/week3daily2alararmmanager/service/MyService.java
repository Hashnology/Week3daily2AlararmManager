package com.example.hashi.week3daily2alararmmanager.service;


import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;

import com.example.hashi.week3daily2alararmmanager.MainActivity;

public class MyService extends IntentService {

    public MyService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {


        String name = intent.getStringExtra("message");
        String email = intent.getStringExtra("email");
        String phone = intent.getStringExtra("phone");
        byte[] imageByteArray = intent.getByteArrayExtra("imageByteArray");
        intent.setAction(MainActivity.FILTER_ACTION_KEY);
        SystemClock.sleep(3000);
//        String echoMessage = "IntentService after a pause of 3 seconds echoes " + message;
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
        broadcastManager.sendBroadcast(intent.putExtra("broadcastMessage", name));
        broadcastManager.sendBroadcast(intent.putExtra("email", email));
        broadcastManager.sendBroadcast(intent.putExtra("phone", phone));
        broadcastManager.sendBroadcast(intent.putExtra("imageByteArray", imageByteArray));


        //        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent.putExtra("broadcastMessage", echoMessage));
    }
}
