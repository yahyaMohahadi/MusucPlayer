package org.maktab.musucplayer.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationManagerCompat;

import org.maktab.musucplayer.R;

public class Notifications {
    private void startTest(Context context) {
        Intent notificationIntent = MusicService.newIntent(context);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 0, notificationIntent, 0);
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(context, "musicchanelid")
                    .setContentTitle("tittle")
                    .setContentText("setContentText")
                    .setSmallIcon(R.drawable.ic_empty_pic)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker("tiker")
                    .build();
            NotificationChannel channel = new NotificationChannel("musicchanelid", "nameee", NotificationManager.IMPORTANCE_DEFAULT);
            context.getSystemService(NotificationManager.class).createNotificationChannel(channel);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(0, notification);
        }

    }
}
