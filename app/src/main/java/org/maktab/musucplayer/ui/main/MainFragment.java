package org.maktab.musucplayer.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.databinding.FragmentMainBinding;
import org.maktab.musucplayer.ui.lists.album.MusicListAlbumFragment;
import org.maktab.musucplayer.ui.lists.artist.MusicListArtistFragment;
import org.maktab.musucplayer.ui.lists.music.MusicListFragment;

public class MainFragment extends Fragment {

    private FragmentMainBinding mBinding;
    private MainFragmentViewModel mViewModel;

    private MusicListFragment mMusicListFragment;
    private MusicListAlbumFragment mAlbumListFragment;
    private MusicListArtistFragment mArtistListFragment;
    private final Fragment[] mFragmentsList = new Fragment[3];


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        mViewModel = new ViewModelProvider(this).get(MainFragmentViewModel.class);
        iniLists();
        mViewModel.setupPager(this, mBinding.ViewPagerLists, mFragmentsList);
        mViewModel.setCallbackRegestery(mBinding.ViewPagerLists);

        //todo find databinding way
        mViewModel.getFieldUpBar().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mBinding.textViewListName.setText(mViewModel.getFieldUpBar().getValue());
            }
        });


        return mBinding.getRoot();
    }


    private void iniLists() {

        mMusicListFragment = MusicListFragment.newInstance();
        mAlbumListFragment = MusicListAlbumFragment.newInstance();
        mArtistListFragment = MusicListArtistFragment.newInstance();
        mFragmentsList[0] = mMusicListFragment;
        mFragmentsList[1] = mArtistListFragment;
        mFragmentsList[2] = mAlbumListFragment;
    }
}