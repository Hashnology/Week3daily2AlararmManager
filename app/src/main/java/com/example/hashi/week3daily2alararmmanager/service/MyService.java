package com.example.hashi.week3daily2alararmmanager.service;


import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.example.hashi.week3daily2alararmmanager.MainActivity;
import com.example.hashi.week3daily2alararmmanager.Utils;

public class MyService extends IntentService {
    public MyService()
    {
        super("MyService");
    }
    Context context;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        context = this;
    }
        @Override
    protected void onHandleIntent(Intent intent) {


        String name = intent.getStringExtra("message");
        String email = intent.getStringExtra("email");
        String phone = intent.getStringExtra("phone");
        byte[] imageByteArray = intent.getByteArrayExtra("imageByteArray");
        intent.setAction(MainActivity.FILTER_ACTION_KEY);
        SystemClock.sleep(5000);
//        String echoMessage = "IntentService after a pause of 3 seconds echoes " + message;


           LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
           broadcastManager.sendBroadcast(intent.putExtra("broadcastMessage", name));
           broadcastManager.sendBroadcast(intent.putExtra("email", email));
           broadcastManager.sendBroadcast(intent.putExtra("phone", phone));
           broadcastManager.sendBroadcast(intent.putExtra("imageByteArray", imageByteArray));
//





            /*MainActivity.MyReceiver      myReceiver = new MainActivity.MyReceiver();
            unregisterReceiver(myReceiver);*/
//            Intent intent1=new Intent(context,MyService.class);
            stopService(intent);

            stopSelf();



        //        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent.putExtra("broadcastMessage", echoMessage));
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
