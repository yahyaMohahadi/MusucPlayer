package org.maktab.musucplayer.view.fragment.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.adapter.MusicListAdapter;
import org.maktab.musucplayer.databinding.FragmentAlbumListBinding;
import org.maktab.musucplayer.repository.SongRepository;
import org.maktab.musucplayer.utils.ListUtils;
import org.maktab.musucplayer.view_model.Music;

public class AlbumListFragment extends Fragment {
    private FragmentAlbumListBinding mBinding;

    RecyclerView mRecyclerViewSongs;
    private ListUtils.Callbacks mCallbacks;
    private MusicListAdapter mMusicListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_album_list,container,false);
        findView(mBinding.getRoot());
        intRecyclerVive();
        mRecyclerViewSongs.setLayoutManager(new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false));

        updateList();
       /* mRecyclerViewSongs.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.HORIZONTAL));*/
        return mBinding.getRoot();
    }

    public void findView(View view) {
        mRecyclerViewSongs = view.findViewById(R.id.recyclerview_album);
    }

    private void intRecyclerVive() {
        mMusicListAdapter = MusicListAdapter.newInstance(
                getActivity(), SongRepository.newInstance(getActivity()).getSongs(),
                ListUtils.States.ALBUMS,
                mCallbacks
        );

        mRecyclerViewSongs.setAdapter(mMusicListAdapter);

    }

    public static AlbumListFragment newInstance(ListUtils.Callbacks callbacks) {
        AlbumListFragment fragment = new AlbumListFragment();
        Bundle args = new Bundle();
        fragment.mCallbacks = callbacks;
        fragment.setArguments(args);
        return fragment;
    }

    void updateList() {
        try {
            mMusicListAdapter.setSongs(Music.newInstance().getSongList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}