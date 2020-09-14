package org.maktab.musucplayer.database;

import android.content.Context;
import android.util.Log;

import org.maktab.musucplayer.model.Song;
import org.maktab.musucplayer.utils.Music;

import java.util.ArrayList;
import java.util.List;

public class SongRepository {

    private List<Song> mSongs;
    private static Context sContext;

    private static SongRepository sRepository;

    private SongRepository() {
    }

    public static SongRepository newInstance(Context context) {
        if (sRepository == null) {
            sRepository = new SongRepository();
            sContext = context.getApplicationContext();
        }
        if (sRepository.mSongs == null) {
            try {
                sRepository.mSongs = Music.getMdediFromContentResolver(sContext);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("QQQ", "erore for geting Music");
            }
        }
        return sRepository;
    }

    public boolean updateSongs() {
        try {
            sRepository.mSongs = Music.getMdediFromContentResolver(sContext);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("QQQ", "erore for updating Music");
            return false;
        }
    }

    public List<Song> getSongs() {
        return mSongs;
    }

    public List<Song> getSongAlbum(String album) {
        List<Song> songAlbum = new ArrayList<>();
        for (Song song : mSongs) {
            if (song.getStringAlbum().equals(album)) {
                songAlbum.add(song);
            }
        }
        return songAlbum;
    }

    public List<Song> getSongArtist(String artist) {
        List<Song> songArtist = new ArrayList<>();
        for (Song song : mSongs) {
            if (song.getStringAlbum().equals(artist)) {
                songArtist.add(song);
            }
        }
        return songArtist;
    }

}
