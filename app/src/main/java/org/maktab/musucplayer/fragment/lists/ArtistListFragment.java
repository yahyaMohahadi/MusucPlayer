package org.maktab.musucplayer.fragment.lists;

import android.net.Uri;
import android.os.Bundle;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.adapter.MusicListAdapter;
import org.maktab.musucplayer.database.SongRepository;


public class ArtistListFragment extends ListFragment {

    private Callbacks mCallbacksl;

    public static ArtistListFragment newInstance(Callbacks callbacks) {
        ArtistListFragment fragment = new ArtistListFragment();
        Bundle args = new Bundle();
        fragment.mCallbacksl = callbacks;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public MusicListAdapter getMusicAdapter() {
        return MusicListAdapter.newInstance(
                getActivity(), SongRepository.newInstance(getActivity()).getSongs(),
                States.ARTISTS,
               mCallbacksl
        );
    }

    @Override
    public int getFragmtView() {
        return R.layout.fragment_artist_list;
    }

    @Override
    public int getRecyclerViewId() {
        return R.id.recyclerview_artist;
    }


}