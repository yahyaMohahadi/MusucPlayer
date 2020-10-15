package org.maktab.musucplayer.view_model;

import android.net.Uri;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import org.maktab.musucplayer.model.Song;
import org.maktab.musucplayer.view_model.player.Music;
import org.maktab.musucplayer.utils.ListUtils;

public class MusicViewModel extends ViewModel {
    private final ListUtils.Callbacks mCallbacks;
    private final Song mSong;
    private String mStringTittle;
    public String mStringArtist;
    private Uri mUriSongImage;
    private ListUtils.States mStates;

    public ObservableField<Uri> resultImageUrl = new ObservableField<>();

    public MusicViewModel(Song song, ListUtils.States states, ListUtils.Callbacks callbacks) {
        mSong = song;
        mStates = states;
        mUriSongImage = song.getUriImage();
        mCallbacks = callbacks;
    }

    public boolean getIsPlaying() {
        try {
            return Music.newInstance().getCurentSong().equals(mSong);
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
    }

    public void onCklick() {
        mCallbacks.itemCalled(mStates, String.valueOf(mSong.getIntId()));
    }

    public void imageUriUpdater() {
        resultImageUrl.set(mUriSongImage);
    }

    public String getTittle() {
        return mStringTittle;
    }

    public void setTittle(String stringTittle) {
        mStringTittle = stringTittle;
    }

    public String getArtist() {
        return mStringArtist;
    }

    public void setArtist(String stringArtist) {
        mStringArtist = stringArtist;
    }
}
