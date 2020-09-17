package org.maktab.musucplayer.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;

public class Music {
    private Context mContext;
    private static Music sInstance;
    private MediaPlayer mediaPlayer;

    private Music() {
    }

    public static Music newInstance(Context context, Uri uri) {
        if (sInstance == null) {
            sInstance = new Music();
            sInstance.mContext = context.getApplicationContext();
            sInstance.mediaPlayer = MediaPlayer.create(context, uri);
        }
        return sInstance;
    }

    public boolean startOver(Uri uri) {
       mediaPlayer.reset();

        try {
            mediaPlayer.setDataSource(mContext, uri);
            mediaPlayer.start();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void puse() {
        if (mediaPlayer.isPlaying())
            mediaPlayer.stop();
    }

    public void startResume() {
        if (!mediaPlayer.isPlaying())
            mediaPlayer.start();
    }
}

