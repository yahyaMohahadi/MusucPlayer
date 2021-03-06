package org.maktab.musucplayer.ui.lists.music;

import android.content.Context;
import android.media.ImageReader;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.musucplayer.adapter.MusicListAdapter;
import org.maktab.musucplayer.data.local.repository.SongRepository;
import org.maktab.musucplayer.data.model.Song;
import org.maktab.musucplayer.ui.Callback;

import java.util.List;

public class MusicListViewModel extends ViewModel {
    private MutableLiveData<List<Song>> mListMutableLiveData = new MutableLiveData<>();
    private MusicListAdapter mAdapter;
    private Callback<Song> mCallback;

    public MusicListViewModel() {
    }

    public void fetchSongs(final Context context) {
        //todo find way to dont send contex
        new Thread(new Runnable() {
            @Override
            public void run() {
                mListMutableLiveData.postValue(SongRepository.newInstance(context).getSongs());
            }
        }).start();

    }

    public void setCallback(Callback<Song> callback) {
        mCallback = callback;
    }

    public MutableLiveData<List<Song>> getListMutableLiveData() {
        return mListMutableLiveData;
    }

    public void setupRacyclerView(RecyclerView recyclerviewSongs) {
        if (mAdapter == null) {
            mAdapter =new MusicListAdapter(mListMutableLiveData.getValue(),mCallback);
            recyclerviewSongs.setLayoutManager(new LinearLayoutManager(recyclerviewSongs.getContext()));
            recyclerviewSongs.setAdapter(mAdapter);

        } else {
            mAdapter.setSongs(mListMutableLiveData.getValue());
        }
    }
}
