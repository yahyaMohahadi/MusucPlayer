package org.maktab.musucplayer.view_model;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.musucplayer.adapter.MusicListAdapter;
import org.maktab.musucplayer.model.Song;
import org.maktab.musucplayer.utils.ListUtils;

import java.util.List;

public class MusicListViewModel extends ViewModel {
    private final Context mContex;
    private final ListUtils.States mStates;
    private final ListUtils.Callbacks mCallbacks;
    private  List<Song> mSongs;
    private MusicListAdapter mAdapter;


    public MusicListViewModel(Context activity, ListUtils.Callbacks callbacks,  ListUtils.States states) {
        mContex = activity.getApplicationContext();
        mStates = states;
        //todo get songs
        mCallbacks = callbacks;

    }

    public void setUpAdapter(RecyclerView recyclerviewSongs) {
        recyclerviewSongs.setLayoutManager(new LinearLayoutManager(mContex));
        recyclerviewSongs.addItemDecoration(new DividerItemDecoration(mContex,
                DividerItemDecoration.VERTICAL));
        mAdapter = MusicListAdapter.newInstance(mCallbacks, mSongs, mStates);
        recyclerviewSongs.setAdapter(mAdapter);
    }

    public void changeSong(Song song) {
    }

    public void setSongs(List<Song> songs) {
        mSongs=songs;
        mAdapter.setSongs(songs);

    }
}
