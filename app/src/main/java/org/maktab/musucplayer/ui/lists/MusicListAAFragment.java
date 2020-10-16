package org.maktab.musucplayer.ui.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.databinding.FragmentAlbumListBinding;
import org.maktab.musucplayer.databinding.FragmentArtistListBinding;


public class MusicListAAFragment extends Fragment {
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
                break;
            case ALBUM:
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
                return fragmentArtistListBinding.getRoot();
            case ALBUM:
                FragmentAlbumListBinding fragmentAlbumListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_album_list, container, false);
                return fragmentAlbumListBinding.getRoot();
        }
        return null;
    }

    public enum StateAA {
        ARTIST, ALBUM
    }
}