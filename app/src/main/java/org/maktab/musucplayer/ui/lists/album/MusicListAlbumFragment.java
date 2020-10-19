package org.maktab.musucplayer.ui.lists.album;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.data.model.Album;
import org.maktab.musucplayer.data.model.Artist;
import org.maktab.musucplayer.databinding.FragmentArtistListBinding;

import java.util.List;


public class MusicListAlbumFragment extends Fragment {

    private AlbumListViewMode mViewModel;
    private FragmentArtistListBinding mBinding;

    public static MusicListAlbumFragment newInstance() {
        MusicListAlbumFragment fragment = new MusicListAlbumFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_artist_list, container, false);
        mViewModel = new ViewModelProvider(this).get(AlbumListViewMode.class);
        mViewModel.setupRecyclerView(mBinding.recyclerviewArtist);
        mViewModel.fetchLiveData(getContext());
        mViewModel.getListMutableLiveData().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albums) {
                mViewModel.setupRecyclerView(mBinding.recyclerviewArtist);
            }
        });
        return mBinding.getRoot();
    }

}