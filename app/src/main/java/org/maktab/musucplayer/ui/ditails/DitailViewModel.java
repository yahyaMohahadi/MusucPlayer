package org.maktab.musucplayer.ui.ditails;

import android.content.Context;
import android.net.Uri;
import android.widget.SeekBar;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import org.maktab.musucplayer.data.local.repository.SongRepository;
import org.maktab.musucplayer.data.model.Song;
import org.maktab.musucplayer.player.Music;

import java.io.IOException;

import static org.maktab.musucplayer.player.Music.StatePlay.PAUSE;
import static org.maktab.musucplayer.player.Music.StatePlay.PLAY;
import static org.maktab.musucplayer.player.Music.StateRepeat.NORMAL;
import static org.maktab.musucplayer.player.Music.StateRepeat.REPEAT;
import static org.maktab.musucplayer.player.Music.StateShuffle.RESPECTIVLY;
import static org.maktab.musucplayer.player.Music.StateShuffle.SHUFFLE;

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

    public void fetchMusic(final Context context) {
        mMusic = Music.newInstance(context, SongRepository.newInstance(context).getSongs());
        setTittle(mMusic.getCurentSong().getStringTitle());

    }

    public void fetchSeeckBar(LifecycleOwner owner) {
        mMusic.getLiveDataCurentSecond().observe(owner, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                setCurentDuraton(String.valueOf(integer));
                setIntCurenDuretionPersont(integer);
            }
        });
        Music.getLiveDataCurentSong().observe(owner, new Observer<Song>() {
            @Override
            public void onChanged(Song song) {
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
        setPlaying(mMusic.getStatePlay() == Music.StatePlay.PLAY ? true : false);
    }

    public void onSeekBarMove(SeekBar seekBar, int progress, boolean fromUser) {
        mMusic.seekFromPersent(progress);
        return;
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
        int prosses = (intCurenDuretionPersont * 100 / Integer.valueOf(getAllDuraton()));
        mIntCurenDuretionPersont = prosses;
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
                setShffle(false);
                break;
            case SHUFFLE:
                setShffle(false);
                mMusic.initStateShuffle(RESPECTIVLY);
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
