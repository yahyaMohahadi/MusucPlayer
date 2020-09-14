package org.maktab.musucplayer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.adapter.ViewPagerAdapter;
import org.maktab.musucplayer.database.SongRepository;
import org.maktab.musucplayer.fragment.lists.AlbumListFragment;
import org.maktab.musucplayer.fragment.lists.ArtistListFragment;
import org.maktab.musucplayer.fragment.lists.MusicListFragment;

public class MainFragment extends Fragment {

    private SongRepository mSongRepository;
    private ViewPager2 mViewPagerMusic;
    private ViewPagerAdapter mViewPagerAdapter;

    private MusicListFragment mMusicListFragment;
    private AlbumListFragment mAlbumListFragment;
    private ArtistListFragment mArtistListFragment;
    Fragment[] mFragmentsList = new Fragment[3];
    private int mIntCurentFragment = 0;

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
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mSongRepository = SongRepository.newInstance(getContext());
        findView(view);
        initViewPager();
        setCallbackRegestery();
        return view;
    }

    private void setCallbackRegestery() {
        mViewPagerMusic.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mIntCurentFragment = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);

            }
        });
    }

    private void initViewPager() {
        if (mViewPagerAdapter == null) {
            iniLists();
            mViewPagerAdapter = new ViewPagerAdapter(getActivity(), mFragmentsList);
        }
        mViewPagerMusic.setAdapter(mViewPagerAdapter);
    }

    private void iniLists() {
        mMusicListFragment = MusicListFragment.newInstance();
        mAlbumListFragment = AlbumListFragment.newInstance();
        mArtistListFragment = ArtistListFragment.newInstance();

        mFragmentsList[0] = mMusicListFragment;
        mFragmentsList[1] = mArtistListFragment;
        mFragmentsList[2] = mAlbumListFragment;
    }

    private void findView(View view) {
        mViewPagerMusic = view.findViewById(R.id.ViewPager_music);
    }
}