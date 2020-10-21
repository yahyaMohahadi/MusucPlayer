package org.maktab.musucplayer.ui.ditails;

import android.net.Uri;
import android.widget.SeekBar;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import org.maktab.musucplayer.data.model.Song;
import org.maktab.musucplayer.service.MusicService;
import org.maktab.musucplayer.service.player.Music;

import java.io.IOException;

import static org.maktab.musucplayer.service.player.Music.StatePlay.PAUSE;
import static org.maktab.musucplayer.service.player.Music.StatePlay.PLAY;
import static org.maktab.musucplayer.service.player.Music.StateRepeat.NORMAL;
import static org.maktab.musucplayer.service.player.Music.StateRepeat.REPEAT;
import static org.maktab.musucplayer.service.player.Music.StateShuffle.RESPECTIVLY;
import static org.maktab.musucplayer.service.player.Music.StateShuffle.SHUFFLE;

public class DitailViewModel extends ViewModel implements Observable {

    private PropertyChangeRegistry callbacks = new PropertyChangeRegistry();
    public ObservableField<Uri> mImageUri = new ObservableField<>();
    private Music mMusic;
    private String mCurentDuraton;
    private String mTittle;
    private String mArtist;
    private boolean mIsShffle;
    private boolean mIsRepeat;
    private boolean mIsPlaying;
    private String mAllDuraton;
    private int mIntCurenDuretionPersont;

    public void fetchMusic(final MusicService musicService) {
        mMusic = musicService.getMusic();
        setUi();
    }

    public void fetchSeeckBar(LifecycleOwner owner) {
        Music.getLiveDataCurentSecond().observe(owner, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                setCurentDuraton(String.valueOf(integer));
                setIntCurenDuretionPersont(integer);

            }
        });
        Music.getLiveDataCurentSong().observe(owner, new Observer<Song>() {
            @Override
            public void onChanged(Song song) {
                if (mMusic != null)
                    setUi();

            }
        });
    }

    private void setUi() {
        mImageUri.set(mMusic.getCurentSong().getUriImage());
        setTittle(mMusic.getCurentSong().getStringTitle());
        setAllDuraton(String.valueOf(mMusic.getIntegerMusicTotal()));
        setArtist(mMusic.getCurentSong().getStringArtist());
        setShffle(mMusic.getStateShuffle() == SHUFFLE);
        setRepeat(mMusic.getStateRepeat() == REPEAT);
        setPlaying(mMusic.getStatePlay() == Music.StatePlay.PLAY);
    }

    public void onSeekBarMove(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser)
            mMusic.seekFromPersent(progress);
    }

    @Bindable
    public int getIntCurenDuretionPersont() {
        return mIntCurenDuretionPersont;
    }

    @Bindable
    public boolean isPlaying() {
        return mIsPlaying;
    }

    public void setPlaying(boolean playing) {
        mIsPlaying = playing;
        callbacks.notifyCallbacks(this, 0, null);
    }

    public void setIntCurenDuretionPersont(int intCurenDuretionPersont) {
        if (getAllDuraton() == null)
            return;
        mIntCurenDuretionPersont = ((intCurenDuretionPersont * 100) / Integer.parseInt(getAllDuraton()));
        callbacks.notifyCallbacks(this, 0, null);
    }

    @Bindable
    public String getArtist() {
        return mArtist;
    }

    @Bindable
    public boolean isShffle() {
        return mIsShffle;
    }

    public void setShffle(boolean shffle) {
        mIsShffle = shffle;
        callbacks.notifyCallbacks(this, 0, null);
    }

    @Bindable
    public boolean isRepeat() {
        return mIsRepeat;
    }

    public void setRepeat(boolean repeat) {
        mIsRepeat = repeat;
        callbacks.notifyCallbacks(this, 0, null);
    }

    public void setArtist(String artist) {
        mArtist = artist;
        callbacks.notifyCallbacks(this, 0, null);
    }

    @Bindable
    public String getAllDuraton() {
        return mAllDuraton;
    }

    public void setAllDuraton(String allDuraton) {
        mAllDuraton = allDuraton;
        callbacks.notifyCallbacks(this, 0, null);
    }

    @Bindable
    public String getTittle() {
        return mTittle;
    }

    public void setTittle(String tittle) {
        mTittle = tittle;
        callbacks.notifyCallbacks(this, 0, null);
    }

    @Bindable
    public String getCurentDuraton() {
        return mCurentDuraton;
    }

    public void setCurentDuraton(String curentDuraton) {
        mCurentDuraton = curentDuraton;
        callbacks.notifyCallbacks(this, 0, null);

    }

    public void onClickRpeat() {
        switch (mMusic.getStateRepeat()) {
            case REPEAT:
                setRepeat(true);
                mMusic.initStateRepeat(NORMAL);
                break;
            case NORMAL:
                setRepeat(false);
                mMusic.initStateRepeat(REPEAT);
                break;
        }


    }

    public void onClickShaffle() {
        switch (mMusic.getStateShuffle()) {

            case RESPECTIVLY:
                mMusic.initStateShuffle(SHUFFLE);
                setShffle(true);
                break;
            case SHUFFLE:
                mMusic.initStateShuffle(RESPECTIVLY);
                setShffle(false);
                break;
        }
    }

    public void onClickNext() {
        try {
            mMusic.initDirection(Music.Direction.NEWXT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickPrevious() {
        try {
            mMusic.initDirection(Music.Direction.PREVIOUS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickPlay() {
        switch (mMusic.getStatePlay()) {
            case PLAY:
                setPlaying(false);
                mMusic.initStatePlay(PAUSE);
                break;
            case PAUSE:
                setPlaying(true);
                mMusic.initStatePlay(PLAY);
                break;
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
