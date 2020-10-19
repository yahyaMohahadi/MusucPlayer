package org.maktab.musucplayer.ui.lists.album;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.musucplayer.adapter.MusicAlbumAdapter;
import org.maktab.musucplayer.data.local.repository.SongRepository;
import org.maktab.musucplayer.data.model.Album;
import org.maktab.musucplayer.data.model.Song;

import java.util.ArrayList;
import java.util.List;

public class AlbumListViewMode extends ViewModel {
    private MusicAlbumAdapter mAdapter;
    private MutableLiveData<List<Album>> mListMutableLiveData = new MutableLiveData<>();

    public AlbumListViewMode() {
    }

    public MutableLiveData<List<Album>> getListMutableLiveData() {
        return mListMutableLiveData;
    }

    public void setupRecyclerView(RecyclerView recyclerviewArtist) {
        if (mAdapter == null) {
            recyclerviewArtist.setLayoutManager(new LinearLayoutManager(recyclerviewArtist.getContext()));
            mAdapter = MusicAlbumAdapter.newInstance(mListMutableLiveData.getValue());
            recyclerviewArtist.setAdapter(mAdapter);
        } else {
            mAdapter.setSongs(mListMutableLiveData.getValue());
            mAdapter.notifyDataSetChanged();
        }
    }

    public void fetchLiveData(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Song> songs = new ArrayList<>();
                songs.addAll(SongRepository.newInstance(context).getSongs());
                mListMutableLiveData.postValue(
                        SongRepository.getAlbumFromSongs(songs)
                );
            }
        }).start();

    }
}
