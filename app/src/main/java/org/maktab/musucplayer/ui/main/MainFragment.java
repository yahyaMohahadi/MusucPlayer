package org.maktab.musucplayer.ui.main;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.adapter.ViewPagerAdapter;
import org.maktab.musucplayer.databinding.FragmentMainBinding;
import org.maktab.musucplayer.data.model.Song;
import org.maktab.musucplayer.ui.lists.albumArtist.MusicListAAFragment;
import org.maktab.musucplayer.ui.lists.music.MusicListFragment;

public class MainFragment extends Fragment {

    private FragmentMainBinding mBinding;

    private ViewPager2 mViewPagerMusic;
    private ViewPagerAdapter mViewPagerAdapter;

    private MusicListFragment mMusicListFragment;
    private MusicListAAFragment mAlbumListFragment;
    private MusicListAAFragment mArtistListFragment;
    Fragment[] mFragmentsList = new Fragment[3];
    public static final String[] mFragmentsName = new String[]{
            "music",
            "artist",
            "album"};
    private TextView mTextViewListName;
    private int mIntCurentFragment = 0;

    /* private ImageButton mImageButtonShuffle;
     private ImageButton mImageButtonPlay;
     private TextView mTextViewPlatBarTittleText;*/

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        findView(mBinding.getRoot());
        setOncklickMusicBar();
        initViewPager();
        setCallbackRegestery();
        Log.d("QQQ","STATRT MAIN");
        return mBinding.getRoot();
    }

    private void setOncklickMusicBar() {
/*        mImageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo

            }
        });
        mImageButtonShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo

            }
        });
        mTextViewPlatBarTittleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startPlayMusicView();

            }
        });*/

    }


    private void startPlayMusicView() {
        //todo start MusicFragmetPlay
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
                initTextViewList();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);

            }
        });
    }

    private void initTextViewList() {
        mTextViewListName.setText(mFragmentsName[mIntCurentFragment]);
    }

    private void initViewPager() {
        //todo int wrong to dont check adapteeer exist but it asure error
       /* if (mViewPagerAdapter == null) {
            iniLists();
            mViewPagerAdapter = new ViewPagerAdapter(getActivity(), mFragmentsList);
        }
        mViewPagerMusic.setAdapter(mViewPagerAdapter);*/
        iniLists();
        mViewPagerAdapter = new ViewPagerAdapter(getActivity(), mFragmentsList);
        mViewPagerMusic.setAdapter(mViewPagerAdapter);
    }

    private void iniLists() {

        mMusicListFragment = MusicListFragment.newInstance();
        mAlbumListFragment = MusicListAAFragment.newInstance(MusicListAAFragment.StateAA.ALBUM);
        mArtistListFragment = MusicListAAFragment.newInstance(MusicListAAFragment.StateAA.ARTIST);

        mFragmentsList[0] = mMusicListFragment;
        mFragmentsList[1] = mArtistListFragment;
        mFragmentsList[2] = mAlbumListFragment;
    }

    private void findView(View view) {
        mViewPagerMusic = view.findViewById(R.id.ViewPager_lists);
        mTextViewListName = view.findViewById(R.id.textView_list_name);
    }


    public void setCurentSong(Song song) {
        //0 = music / 1 = artist/ 2 = album
        //todo make better ui when calles album or lsit
        switch (mIntCurentFragment) {
            case 0: {
                //mMusicListFragment.changeCurentSong(song);
                break;
            }
            case 1: {
                break;
            }
            case 2: {
                break;
            }
        }

    }
}