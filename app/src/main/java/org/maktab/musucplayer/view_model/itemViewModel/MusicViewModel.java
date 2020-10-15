package org.maktab.musucplayer.view_model.itemViewModel;

import android.net.Uri;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import org.maktab.musucplayer.model.Song;
import org.maktab.musucplayer.view_model.MainViewModel;
import org.maktab.musucplayer.player.Music;
import org.maktab.musucplayer.utils.ListUtils;

public class MusicViewModel extends ViewModel {
    private final Song mSong;
    private String mStringTittle;
    public String mStringArtist;
    private Uri mUriSongImage;

    public ObservableField<Uri> resultImageUrl = new ObservableField<>();

    public MusicViewModel(Song song) {
        mSong = song;
        mUriSongImage = song.getUriImage();
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
