package org.maktab.musucplayer.view_model;

import androidx.lifecycle.MutableLiveData;

import org.maktab.musucplayer.model.Song;
import org.maktab.musucplayer.player.Music;

import java.util.List;

public class MusicViewModel {
    private static MusicViewModel sViewModel;

    private MutableLiveData<Music> mLiveDataOnlineMusic = new MutableLiveData<>();
    private MutableLiveData<List<Song>> mMutableLiveDataAllSongs = new MutableLiveData<>();

    private MusicViewModel() {
    }

    public static MusicViewModel newInstance() {
        if (sViewModel == null) {
            sViewModel = new MusicViewModel();
        }
        return sViewModel;
    }

    public MutableLiveData<List<Song>> getMutableLiveDataAllSongs() {
        return mMutableLiveDataAllSongs;
    }

    public void setMutableLiveDataAllSongs(MutableLiveData<List<Song>> mutableLiveDataAllSongs) {
        mMutableLiveDataAllSongs = mutableLiveDataAllSongs;
    }


    public static void setViewModel(MusicViewModel viewModel) {
        sViewModel = viewModel;
    }

    public MutableLiveData<Music> getLiveDataOnlineMusic() {
        return mLiveDataOnlineMusic;
    }

    public void setLiveDataOnlineMusic(MutableLiveData<Music> liveDataOnlineMusic) {
        mLiveDataOnlineMusic = liveDataOnlineMusic;
    }
}
