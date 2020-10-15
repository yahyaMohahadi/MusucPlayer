package org.maktab.musucplayer.view_model.listViewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.musucplayer.adapter.MusicListAdapter;
import org.maktab.musucplayer.model.Song;

import java.util.List;

public class MusicListViewModel extends ViewModel {
    private Context mContex;

    private List<Song> mSongs;
    private MusicListAdapter mAdapter;


    public MusicListViewModel(Context activity) {
        mContex = activity.getApplicationContext();
        //todo get song
    }

    public void setContex(Context contex) {
        mContex = contex;
    }

    public MusicListViewModel() {
    }


    public void setUpAdapter(RecyclerView recyclerviewSongs) {
        recyclerviewSongs.setLayoutManager(new LinearLayoutManager(mContex));
        recyclerviewSongs.addItemDecoration(new DividerItemDecoration(mContex,
                DividerItemDecoration.VERTICAL));
        mAdapter = MusicListAdapter.newInstance(mSongs);
        recyclerviewSongs.setAdapter(mAdapter);
    }

    public void changeSong(Song song) {
    }

    public void setSongs(List<Song> songs) {
        mSongs = songs;
        mAdapter.setSongs(songs);

    }
}
