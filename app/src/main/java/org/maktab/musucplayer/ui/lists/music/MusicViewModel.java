package org.maktab.musucplayer.ui.lists.music;

import android.net.Uri;
import android.util.Log;

import androidx.databinding.ObservableField;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.data.model.Song;
import org.maktab.musucplayer.ui.Callback;
import org.maktab.musucplayer.utils.StringLimiter;

public class MusicViewModel {
    private Song mSong;
    private Callback<Song> mSongCallback;
    public ObservableField<Uri> resultImageUrl = new ObservableField<>();

    public void onClick() {
        mSongCallback.onClick(mSong, false);
    }

    public MusicViewModel(Song song, Callback<Song> songCallback) {
        mSongCallback = songCallback;
        mSong = (song);
        imageUrlUpdated();
    }

    public String getTittle() {
        return StringLimiter.limitString(mSong.getStringTitle(), StringLimiter.LIMIT_CHARE_TITTLE);
    }

    public String getArtist() {
        return StringLimiter.limitString(mSong.getStringArtist(), StringLimiter.LIMIT_CHARE_AETIST);
    }

    public ObservableField<Uri> getResultImageUrl() {
        return resultImageUrl;
    }

    public void imageUrlUpdated() {
        if (mSong.getUriImage() != null) {
            resultImageUrl.set(mSong.getUriImage());
        }
    }
}
