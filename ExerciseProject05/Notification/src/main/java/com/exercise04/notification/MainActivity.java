package com.exercise04.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    NotificationManager notificationManager = null;
    NotificationCompat.Builder builder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void send(View view) {
        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(MainActivity.this);

        //ChannelId为"1",ChannelName为"Channel1"
        NotificationChannel channel = new NotificationChannel("1",
                "Channel1", NotificationManager.IMPORTANCE_DEFAULT);
        channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
        notificationManager.createNotificationChannel(channel);

        builder.setAutoCancel(true);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        //builder.setLargeIcon(R.mipmap.ic_launcher_round);
        builder.setContentTitle("通知");
        builder.setContentText("通知内容");
        builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
        builder.setWhen(System.currentTimeMillis());
        Intent intent = new Intent(MainActivity.this,MyActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                MainActivity.this,0,intent,0);
        builder.setContentIntent(pendingIntent);
        notificationManager.notify(1,builder.build());
    }
}
