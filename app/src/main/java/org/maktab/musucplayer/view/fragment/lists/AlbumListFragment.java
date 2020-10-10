package org.maktab.musucplayer.view.fragment.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.databinding.FragmentAlbumListBinding;
import org.maktab.musucplayer.utils.ListUtils;
import org.maktab.musucplayer.view_model.AlbumListViewModel;

public class AlbumListFragment extends Fragment {

    private FragmentAlbumListBinding mBinding;
    private AlbumListViewModel mViewModel;
    private static ListUtils.Callbacks mCallbacks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new AlbumListViewModel(getContext(),mCallbacks);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_album_list, container, false);
        mViewModel.setupRecyclerView(mBinding.recyclerviewAlbum);
        return mBinding.getRoot();
    }

    public static AlbumListFragment newInstance(ListUtils.Callbacks callbacksLists) {
        AlbumListFragment fragment = new AlbumListFragment();
        Bundle args = new Bundle();
        mCallbacks= callbacksLists;
        fragment.setArguments(args);
        return fragment;
    }
}