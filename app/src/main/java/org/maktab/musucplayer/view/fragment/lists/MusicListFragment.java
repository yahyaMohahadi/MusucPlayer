package org.maktab.musucplayer.view.fragment.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.databinding.FragmentMusicListBinding;
import org.maktab.musucplayer.model.Song;
import org.maktab.musucplayer.utils.ListUtils;
import org.maktab.musucplayer.view_model.MusicListViewModel;

import java.util.List;

public class MusicListFragment extends Fragment {
    private FragmentMusicListBinding mBinding;
    private static MusicListViewModel mViewModel;


    public static MusicListFragment newInstance(ListUtils.Callbacks callbacks, ListUtils.States states) {
        if (states == ListUtils.States.MUSIC_ALBUM
                || states == ListUtils.States.MUSICS
                || states == ListUtils.States.MUSIC_ARTIST) {
            MusicListFragment fragment = new MusicListFragment();
            return fragment;
        }
        return null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewModel =new  ViewModelProvider(this).get(MusicListViewModel.class);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_music_list, container, false);
        mViewModel.setUpAdapter(mBinding.recyclerviewSongs);
        return mBinding.getRoot();
    }

    public void setSongs(List<Song> songs){
        mViewModel.setSongs(songs);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}