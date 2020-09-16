package org.maktab.musucplayer.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
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
    public static final String[] mFragmentsName = new String[]{"musics", "artists", "album"};
    private TextView mTextViewListName;
    private int mIntCurentFragment = 0;

    private ImageButton mImageButtonShuffle;
    private ImageButton mImageButtonPlay;
    private TextView mTextViewPlatBarTittleText;

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
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mSongRepository = SongRepository.newInstance(getContext());
        findView(view);
        setOncklickMusicBar();
        initViewPager();
        setCallbackRegestery();

        return view;
    }

    private void setOncklickMusicBar() {
        mImageButtonPlay.setOnClickListener(new View.OnClickListener() {
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
        });

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
        mTextViewListName = view.findViewById(R.id.textView_list_name);
        mImageButtonShuffle = view.findViewById(R.id.imageButton_main_fragment_shuffle);
        mImageButtonPlay = view.findViewById(R.id.imageButtin_main_fragment_play);
        mTextViewPlatBarTittleText = view.findViewById(R.id.textView_play_bar_title);
    }
}