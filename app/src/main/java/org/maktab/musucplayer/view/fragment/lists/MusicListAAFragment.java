package org.maktab.musucplayer.view.fragment.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.databinding.FragmentAlbumListBinding;
import org.maktab.musucplayer.databinding.FragmentArtistListBinding;
import org.maktab.musucplayer.view_model.listViewModel.AlbumListViewModel;
import org.maktab.musucplayer.view_model.listViewModel.ArtistListViewModel;


public class MusicListAAFragment extends Fragment {
    private ArtistListViewModel mArtistListViewModel;
    private AlbumListViewModel mAlbumListViewModel;
    private StateAA mState;

    public static MusicListAAFragment newInstance(StateAA state) {
        MusicListAAFragment fragment = new MusicListAAFragment();
        fragment.mState = state;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (mState) {

            case ARTIST:
                mArtistListViewModel = new ArtistListViewModel(getContext());
                break;
            case ALBUM:
                mAlbumListViewModel = new AlbumListViewModel(getContext());
                break;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        switch (mState) {
            case ARTIST:
                FragmentArtistListBinding fragmentArtistListBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_artist_list,container,false);
                fragmentArtistListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_artist_list, container, false);
                mArtistListViewModel.setupRecyclerView(fragmentArtistListBinding.recyclerviewArtist);
                return fragmentArtistListBinding.getRoot();
            case ALBUM:
                FragmentAlbumListBinding fragmentAlbumListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_album_list, container, false);
                mAlbumListViewModel.setupRecyclerView(fragmentAlbumListBinding.recyclerviewAlbum);
                return fragmentAlbumListBinding.getRoot();
        }
        return null;
    }

    public enum StateAA {
        ARTIST, ALBUM
    }
}