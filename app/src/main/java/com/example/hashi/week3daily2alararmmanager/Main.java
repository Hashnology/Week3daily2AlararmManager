package com.example.hashi.week3daily2alararmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hashi.week3daily2alararmmanager.service.AlarmReceiver;

import java.util.Date;

public class Main extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

/*send data to alrarm receiver by using alaram amnager*/
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent broadcast_intent = new Intent(this, AlarmReceiver.class);
        broadcast_intent.putExtra("test", "Hashi is here"); //data to pass

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,  broadcast_intent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, 5000, pendingIntent);
    }
}