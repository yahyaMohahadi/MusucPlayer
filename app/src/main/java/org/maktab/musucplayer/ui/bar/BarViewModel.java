package org.maktab.musucplayer.ui.bar;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import org.maktab.musucplayer.data.model.Song;
import org.maktab.musucplayer.service.MusicService;
import org.maktab.musucplayer.service.player.Music;
import org.maktab.musucplayer.utils.StringLimiter;

import java.io.IOException;

//todo find beter way for dataa binding
public class BarViewModel extends ViewModel implements Observable {
    private MutableLiveData<Music> mMusic = new MutableLiveData<>();
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

    public void fetchService(@NonNull MusicService service) {
        mMusic.postValue(service.getMusic());
    }


    public void setSongUi(Song song) {
        setTittle(song.getStringTitle());
        setArtist(song.getStringArtist());
        mImageUri.set(song.getUriImage());
        if (mMusic.getValue() != null)
            setPlaying(mMusic.getValue().getStatePlay() == Music.StatePlay.PLAY);
    }

    public void onNextClicked() {
        try {
            mMusic.getValue().initDirection(Music.Direction.NEWXT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onPreviosClicked() {
        try {
            mMusic.getValue().initDirection(Music.Direction.PREVIOUS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onPuseClicked() {
        switch (mMusic.getValue().getStatePlay()) {
            case PAUSE: {
                mMusic.getValue().initStatePlay(Music.StatePlay.PLAY);
                setPlaying(true);
                break;
            }
            case PLAY: {
                mMusic.getValue().initStatePlay(Music.StatePlay.PAUSE);
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

    public void fechMusic(PlayingBarFragment playingBarFragment) {
        //todo refactor and delete this
        mMusic.observe(playingBarFragment, new Observer<Music>() {
            @Override
            public void onChanged(Music music) {
                if (mMusic == null) {
                    return;
                }
                if (Music.getLiveDataCurentSong().getValue() != null)
                    setSongUi(Music.getLiveDataCurentSong().getValue());
            }
        });
        Music.getLiveDataCurentSong().observe(playingBarFragment, new Observer<Song>() {
            @Override
            public void onChanged(Song song) {
                if (mMusic != null && song != null)
                    setSongUi(song);
            }
        });
    }
}
