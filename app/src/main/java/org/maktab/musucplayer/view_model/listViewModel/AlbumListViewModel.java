package org.maktab.musucplayer.view_model.listViewModel;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.musucplayer.adapter.MusicAlbumAdapter;
import org.maktab.musucplayer.adapter.MusicArtistAdapter;
import org.maktab.musucplayer.model.Album;
import org.maktab.musucplayer.model.Song;
import org.maktab.musucplayer.repository.SongRepository;
import org.maktab.musucplayer.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class AlbumListViewModel extends ViewModel {
    private Context mContext;
    private List<Album> mAlbums;

    public AlbumListViewModel(Context context) {
        mContext = context.getApplicationContext();
        mAlbums = SongRepository.getAlbumFromSongs((ArrayList<Song>) SongRepository.newInstance(mContext).getSongs());
    }

    public void setupRecyclerView(RecyclerView recyclerviewAlbum) {
        //todo change it for tablet to be 3
        recyclerviewAlbum.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
        MusicAlbumAdapter adapter= MusicAlbumAdapter.newInstance(mAlbums);
        recyclerviewAlbum.setAdapter(adapter);
    }
}
