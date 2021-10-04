package com.example.uinotification;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.StatusBarManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    private static final Object EXTRA_NOTIFICATION_ID =12 ;
    NotificationManagerCompat notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     notificationManager= NotificationManagerCompat.from(this);

    }
        @RequiresApi(api = Build.VERSION_CODES.N)
        public void shownotification(View view) throws InterruptedException {


        Notification.Builder builder1=new Notification.Builder(this)
                .setSmallIcon(R.drawable.comment)
                .setContentTitle("Instant Message")
                .setContentText("Hey, what are you doing for lunch?")
                .setAutoCancel(true);

        NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            @SuppressLint("WrongConstant") NotificationChannel channel=new NotificationChannel("example","Android",NotificationManager.IMPORTANCE_DEFAULT);
                manager.createNotificationChannel(channel);
            builder1.setChannelId("example");

        }


            RemoteViews collapsed=new RemoteViews(getPackageName(),R.layout.notification_custom);
             Notification.Builder builder=new Notification.Builder(this)
                    .setSmallIcon(R.drawable.comment)
                    .setContentTitle("Instant Message")
                    .setCustomBigContentView(collapsed)

                    .setAutoCancel(true);
         manager.notify(123,builder1.build());
            manager.notify(1234,builder.build());


    }


  //      @RequiresApi(api = Build.VERSION_CODES.N)
//        public void show(NotificationManager manag){
//            NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//
//            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
//            {
//                NotificationChannel channel=new NotificationChannel("example","And",NotificationManager.IMPORTANCE_NONE);
//
//                manager.createNotificationChannel(channel);
//                builder.setChannelId("example");
//
//
//            }
//
//            boolean check=false;
//            StatusBarNotification[] n=manag.getActiveNotifications();
//            for (StatusBarNotification noti:n){
//
//                Log.i("check",""+noti.isOngoing());
//                    check=true;
//
//            }
//            if(check)
//            manager.notify(1234,builder.build());
//
//
//        }

}