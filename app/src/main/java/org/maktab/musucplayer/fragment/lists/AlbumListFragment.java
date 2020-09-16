package org.maktab.musucplayer.fragment.lists;

import android.net.Uri;
import android.os.Bundle;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.adapter.MusicListAdapter;
import org.maktab.musucplayer.database.SongRepository;

public class AlbumListFragment extends ListFragment {

    public static AlbumListFragment newInstance() {
        AlbumListFragment fragment = new AlbumListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public MusicListAdapter getMusicAdapter() {
        return MusicListAdapter.newInstance(
                getActivity(), SongRepository.newInstance(getActivity()).getSongs(),
                MusicListAdapter.State.ALBUMS,
                new MusicListAdapter.Callbacks() {
                    @Override
                    public void onItemSelected(Uri musicUri) {

                    }
                }
        );

    }

    @Override
    public int getFragmtView() {
        return R.layout.fragment_album_list;
    }

    @Override
    public int getRecyclerViewId() {
        return R.id.recyclerview_album;
    }
}