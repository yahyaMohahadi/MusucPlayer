package org.maktab.musucplayer.view.fragment.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.adapter.MusicListAdapter;
import org.maktab.musucplayer.databinding.FragmentAlbumListBinding;
import org.maktab.musucplayer.repository.SongRepository;
import org.maktab.musucplayer.utils.ListUtils;
import org.maktab.musucplayer.view_model.Music;


public class ArtistListFragment extends Fragment {
    private FragmentAlbumListBinding mBinding;
    RecyclerView mRecyclerViewSongs;
    private ListUtils.Callbacks mCallbacksl;
    private MusicListAdapter mMusicListAdapter;

    public static ArtistListFragment newInstance(ListUtils.Callbacks callbacks) {
        ArtistListFragment fragment = new ArtistListFragment();
        Bundle args = new Bundle();
        fragment.mCallbacksl = callbacks;
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_artist_list,container,false);
        findView(mBinding.getRoot());
        intRecyclerVive();
        mRecyclerViewSongs.setLayoutManager( new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false));

        updateList();
       /* mRecyclerViewSongs.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.HORIZONTAL));*/
        return mBinding.getRoot();
    }

    protected LinearLayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }



    public void findView(View view) {
        mRecyclerViewSongs = view.findViewById(R.id.recyclerview_artist);
    }

    private void intRecyclerVive() {
        mMusicListAdapter = MusicListAdapter.newInstance(
                getActivity(), SongRepository.newInstance(getActivity()).getSongs(),
                ListUtils.States.ARTISTS,
                mCallbacksl
        );
        mRecyclerViewSongs.setAdapter(mMusicListAdapter);

    }

    void updateList() {
        try {
            mMusicListAdapter.setSongs(Music.newInstance().getSongList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}