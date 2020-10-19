package org.maktab.musucplayer.ui.main.activity;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import org.maktab.musucplayer.data.local.repository.SongRepository;
import org.maktab.musucplayer.data.model.Album;
import org.maktab.musucplayer.data.model.Artist;
import org.maktab.musucplayer.data.model.Song;
import org.maktab.musucplayer.player.Music;
import org.maktab.musucplayer.ui.Callback;

public class MainActivityViewModel extends ViewModel {
    private Callback[] mCallbacks = new Callback[3];
    private Music mMusic;

    public void setMusic(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mMusic = Music.newInstance(context, SongRepository.newInstance(context).getSongs());
            }
        }).start();
    }

    public void setCallbacks(final Context applicationContext) {

        mCallbacks[0] = new Callback<Song>() {
            @Override
            public void onClick(Song song, boolean allCalled) {
                if (allCalled) {
                    mMusic.setSongList(SongRepository.newInstance(applicationContext).getSongs());
                }
                mMusic.playSong(song);
            }
        };
        mCallbacks[1] = new Callback<Artist>() {
            @Override
            public void onClick(Artist artist, boolean all) {
                mMusic.setSongList(artist.getSongArtist());
            }
        };
        mCallbacks[2] = new Callback<Album>() {
            @Override
            public void onClick(Album album, boolean all) {
                mMusic.setSongList(album.getSongAlbum());
            }
        };
    }

    public Callback[] getCallbacks() {
        return mCallbacks;
    }
}
