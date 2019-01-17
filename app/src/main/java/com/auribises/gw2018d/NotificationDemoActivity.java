package com.auribises.gw2018d;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;

public class NotificationDemoActivity extends AppCompatActivity {

    Notification notification;
    NotificationManager notificationManager;
    NotificationCompat.Builder builder;
    NotificationChannel notificationChannel;

    String id = "NotiId";
    String channel = "MyNotiChannel";

    MyReceiver myReceiver;
    YourReceiver yourReceiver;

    void initReceiver(){
        myReceiver = new MyReceiver();
        yourReceiver = new YourReceiver();

        IntentFilter intentFilter = new IntentFilter();

        // Built In Actions -> System Generated Actions
        intentFilter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW);

        intentFilter.addDataScheme("package");

        // User Defined Action
        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction("a.b.c.d");

        registerReceiver(myReceiver,intentFilter);

        LocalBroadcastManager.getInstance(this).registerReceiver(yourReceiver,intentFilter1);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_demo);

        initReceiver();

    }

    void showNotification(){

        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            notificationChannel = new NotificationChannel(id,channel,NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);

            builder = new NotificationCompat.Builder(this,id);
            builder.setContentTitle("This is Title");
            builder.setContentText("This is Text");
            builder.setSmallIcon(R.drawable.book);

            Intent intent = new Intent(NotificationDemoActivity.this, NavigationDemoActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,101, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setContentIntent(pendingIntent);

            // We can make Notification to look like Big Style
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText("This is Big Text"));
            // Big Style Notification can have Buttons
            builder.addAction(android.R.drawable.ic_menu_add,"Add",pendingIntent);
            builder.addAction(android.R.drawable.ic_menu_delete,"Delete",pendingIntent);

            notification = builder.build();

            notificationManager.notify(101, notification);
        }

    }

    public void clickHandler(View view){
        //showNotification();
        Intent intent = new Intent("a.b.c.d");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String packageName = intent.getData().getSchemeSpecificPart();

            if(action.equals(Intent.ACTION_PACKAGE_REMOVED)){
                Log.i("NotificationDemo","Package Removed: "+packageName);
            }

            if(action.equals(Intent.ACTION_PACKAGE_ADDED)){
                Log.i("NotificationDemo","Package Added");
            }

            if(action.equals(Intent.ACTION_BATTERY_LOW)){
                Log.i("NotificationDemo","Battery Low");
            }


        }
    }

    class YourReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals("a.b.c.d")){
                Log.i("NotificationDemo","a.b.c.d");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(myReceiver);
    }
}
