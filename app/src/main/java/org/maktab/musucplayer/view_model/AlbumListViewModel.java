package org.maktab.musucplayer.view_model;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.musucplayer.adapter.MusicAlbumAdapter;
import org.maktab.musucplayer.model.Album;
import org.maktab.musucplayer.model.Song;
import org.maktab.musucplayer.repository.SongRepository;

import java.util.ArrayList;
import java.util.List;

public class AlbumListViewModel {
    private Context mContext;
    private List<Album> mAlbums;
    public final ObservableField<String> imageUrl;

    public AlbumListViewModel(Context context) {
        mContext = context.getApplicationContext();
        mAlbums = SongRepository.getAlbumFromSongs((ArrayList<Song>) SongRepository.newInstance(mContext).getSongs());
        imageUrl = new ObservableField<>();

    }

    public void setupRecyclerView(RecyclerView recyclerviewAlbum) {
        recyclerviewAlbum.setLayoutManager(new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false));
        recyclerviewAlbum.setAdapter(MusicAlbumAdapter.newInstance(mAlbums));
    }
}
