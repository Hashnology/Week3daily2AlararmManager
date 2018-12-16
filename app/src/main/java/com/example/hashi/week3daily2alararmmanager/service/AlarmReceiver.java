package com.example.hashi.week3daily2alararmmanager.service;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    String driverStatus = "";

    @Override
    public void onReceive(final Context context, Intent intent) {
/*alram receiver receives data from alaram manger*/
        Toast.makeText(context, "Alarm has been received "+intent.getStringExtra("test"), Toast.LENGTH_LONG).show();
        Intent intent1 = new Intent(context, MyForeGroundService.class);
        intent1.setAction(MyForeGroundService.ACTION_START_FOREGROUND_SERVICE);
        context.startService(intent1);
    }

}