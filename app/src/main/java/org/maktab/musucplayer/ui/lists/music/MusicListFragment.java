package org.maktab.musucplayer.ui.lists.music;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.data.model.Song;
import org.maktab.musucplayer.databinding.FragmentMusicListBinding;
import org.maktab.musucplayer.ui.Callback;

import java.util.List;

public class MusicListFragment extends Fragment {
    private FragmentMusicListBinding mBinding;
    private MusicListViewModel mViewModel;
    private Callback<Song> mCallback;


    public static MusicListFragment newInstance(Callback<Song> callback) {
        MusicListFragment fragment = new MusicListFragment();
        fragment.mCallback = callback;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_music_list, container, false);
        mViewModel = new ViewModelProvider(this).get(MusicListViewModel.class);
        mViewModel.setCallback(mCallback);
        mViewModel.fetchSongs(this.getContext());
        addDividerRecyclerView();
        mViewModel.setupRacyclerView(mBinding.recyclerviewSongs);
        mViewModel.getListMutableLiveData().observe(this, new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                mViewModel.setupRacyclerView(mBinding.recyclerviewSongs);
            }
        });
        return mBinding.getRoot();
    }

    public void addDividerRecyclerView() {
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        //todo add divider graadient or drawble option
        mBinding.recyclerviewSongs.addItemDecoration(divider);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}