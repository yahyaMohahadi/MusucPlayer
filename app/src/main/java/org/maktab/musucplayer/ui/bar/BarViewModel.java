package org.maktab.musucplayer.ui.bar;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import org.maktab.musucplayer.data.local.repository.SongRepository;
import org.maktab.musucplayer.data.model.Song;
import org.maktab.musucplayer.service.player.Music;
import org.maktab.musucplayer.utils.StringLimiter;

import java.io.IOException;

//todo find beter way for dataa binding
public class BarViewModel extends ViewModel implements Observable {
    private Music mMusic;
    private PropertyChangeRegistry callbacks = new PropertyChangeRegistry();
    public ObservableField<Uri> mImageUri = new ObservableField<>();
    private String tittle;
    private String artist;
    private boolean isPlaying;

    public BarViewModel() {
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
        callbacks.notifyCallbacks(this, 0, null);
    }

    @Bindable
    public String getArtist() {
        return StringLimiter.limitString(artist, StringLimiter.LIMIT_CHARE_AETIST);
    }

    public void setArtist(String artist) {
        this.artist = artist;
        callbacks.notifyCallbacks(this, 0, null);
    }

    @Bindable
    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
        callbacks.notifyCallbacks(this, 0, null);
    }

    @Bindable
    public String getTittle() {
        return StringLimiter.limitString(tittle, StringLimiter.LIMIT_CHARE_BAR_TITTLE);
    }

    public void fetchMusic(@NonNull final Context application, LifecycleOwner owner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mMusic = Music.newInstance(application, SongRepository.newInstance(application).getSongs());
            }
        }).start();
        Music.getLiveDataCurentSong().observe(owner, new Observer<Song>() {
            @Override
            public void onChanged(Song song) {
                if (mMusic != null)
                    setSongUi(song);
            }
        });
    }

    public void setSongUi(Song song) {
        setTittle(song.getStringTitle());
        setArtist(song.getStringArtist());
        mImageUri.set(song.getUriImage());
        setPlaying(mMusic.getStatePlay() == Music.StatePlay.PLAY ? true : false);
    }

    public void onNextClicked() {
        try {
            mMusic.initDirection(Music.Direction.NEWXT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onPreviosClicked() {
        try {
            mMusic.initDirection(Music.Direction.PREVIOUS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onPuseClicked() {
        switch (mMusic.getStatePlay()) {
            case PAUSE: {
                mMusic.initStatePlay(Music.StatePlay.PLAY);
                setPlaying(true);
                break;
            }
            case PLAY: {
                mMusic.initStatePlay(Music.StatePlay.PAUSE);
                setPlaying(false);
                break;
            }
        }

    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.remove(callback);
    }
}
