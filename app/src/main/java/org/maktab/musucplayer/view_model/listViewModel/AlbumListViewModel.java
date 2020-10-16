package org.maktab.musucplayer.view_model.listViewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.musucplayer.adapter.MusicAlbumAdapter;
import org.maktab.musucplayer.model.Album;
import org.maktab.musucplayer.model.Song;
import org.maktab.musucplayer.repository.SongRepository;
import org.maktab.musucplayer.view_model.MusicViewModel;

import java.util.ArrayList;
import java.util.List;

public class AlbumListViewModel extends ViewModel {
    private Context mContext;
    private List<Album> mAlbums;

    public AlbumListViewModel(Context context) {
        mContext = context.getApplicationContext();
        List<Song> all = MusicViewModel.newInstance().getMutableLiveDataAllSongs().getValue();
        mAlbums = SongRepository.getAlbumFromSongs((ArrayList<Song>) all);
    }

    public void setupRecyclerView(RecyclerView recyclerviewAlbum) {
        //todo change it for tablet to be 3
        recyclerviewAlbum.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
        MusicAlbumAdapter adapter = MusicAlbumAdapter.newInstance(mAlbums);
        recyclerviewAlbum.setAdapter(adapter);
    }
}
