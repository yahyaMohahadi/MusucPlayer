package org.maktab.musucplayer;

import android.content.Context;

import org.maktab.musucplayer.data.local.repository.SongRepository;

public class Application extends android.app.Application {
    private Context mContex;

    @Override
    public void onCreate() {
        super.onCreate();
        mContex = getApplicationContext();
        startFilingRepository();
    }

    //todo refactor and delete this cuse it cuse crash for first time start (no permision)
    private void startFilingRepository() {
        new Thread(new Runnable() {
            @Override
            public void run(){
                while (true) {
                    SongRepository songRepository = SongRepository.newInstance(Application.this);
                    if (songRepository.getSongs() != null)
                        return;
                }
            }
        }).start();
    }
}
