package org.maktab.musucplayer.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.data.local.repository.SongRepository;
import org.maktab.musucplayer.service.player.Music;
import org.maktab.musucplayer.ui.main.activity.MainActivity;

public class MusicService extends Service {
    public static final String CHANNEL_ID = "musicForegroundService";
    public static final String KEY_STRING_ORDER = "org.maktab.musucplayer.serviceORDER";
    public static final String KEY_STRING_stop = "kill";
    private LocalBinder mBinder = new LocalBinder();
    private Music mMusic;

    public class LocalBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, MusicService.class);
    }

    public static Intent newIntent(Context context, String order) {
        Intent intent = new Intent(context, MusicService.class);
        intent.putExtra(KEY_STRING_ORDER, order);
        return intent;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        setupNotification();
        mMusic = Music.newInstance(this, SongRepository.newInstance(this).getSongs());

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getStringExtra(KEY_STRING_ORDER) != null && intent.getStringExtra(KEY_STRING_ORDER).equals(KEY_STRING_stop)) {
            if (mMusic == null || mMusic.getStatePlay().equals(Music.StatePlay.PAUSE)) {
                stopSelf();
            }
        }
        return START_NOT_STICKY;
    }

    public Music getMusic() {
        return mMusic;
    }

    public void setupNotification() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.service_notification);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_empty_pic)
                .setContentIntent(pendingIntent)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setContentTitle("music")
                .setContentText("artist")
                /*    .setCustomContentView(remoteViews)
                    .setCustomBigContentView(remoteViews)*/
                .build();

        startForeground(1, notification);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            serviceChannel.setDescription("this is for music playing");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
