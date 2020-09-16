package org.maktab.musucplayer.fragment.lists;

import android.net.Uri;
import android.os.Bundle;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.adapter.MusicListAdapter;
import org.maktab.musucplayer.database.SongRepository;


public class ArtistListFragment extends ListFragment {


    public static ArtistListFragment newInstance() {
        ArtistListFragment fragment = new ArtistListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public MusicListAdapter getMusicAdapter() {
        return MusicListAdapter.newInstance(
                getActivity(), SongRepository.newInstance(getActivity()).getSongs(),
                MusicListAdapter.State.ARTIST,
                new MusicListAdapter.Callbacks() {
                    @Override
                    public void onItemSelected(Uri musicUri) {

                    }
                }
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