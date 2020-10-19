package org.maktab.musucplayer.ui.lists.music;

import android.net.Uri;
import android.util.Log;

import androidx.databinding.ObservableField;

import org.maktab.musucplayer.data.model.Song;
import org.maktab.musucplayer.utils.StringLimiting;

public class MusicViewModel {
    private Song mSong;

    public ObservableField<Uri> resultImageUrl = new ObservableField<>();

    public void onClick() {

    }

    public MusicViewModel(Song song) {
        mSong = (song);
        imageUrlUpdated();
    }

    public String getTittle() {
        return StringLimiting.limitString(mSong.getStringTitle(), StringLimiting.LIMIT_CHARE_TITTLE);
    }

    public String getArtist() {
        return StringLimiting.limitString(mSong.getStringArtist(), StringLimiting.LIMIT_CHARE_AETIST);
    }

    public ObservableField<Uri> getResultImageUrl() {
        return resultImageUrl;
    }

    public void imageUrlUpdated() {
        resultImageUrl.set(mSong.getUriImage());
    }
}
