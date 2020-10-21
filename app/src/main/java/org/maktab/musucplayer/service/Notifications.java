package org.maktab.musucplayer.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import androidx.core.app.NotificationManagerCompat;

import org.maktab.musucplayer.R;

public class Notifications {
    //todo logic notification seperating heare from musicService
 /*   private boolean mBound;
    private MusicService mService;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService = ((MusicService.LocalBinder) iBinder).getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = MusicService.newIntent(this);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        startService(MusicService.newIntent(this, MusicService.KEY_STRING_stop));
        mBound = false;
    }*/
}
