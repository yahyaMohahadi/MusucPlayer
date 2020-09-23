package org.maktab.musucplayer.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.model.Song;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Random;


public class PlayingMusicFragment extends Fragment {


    public static final String KEY_SONGS = "org.maktab.musucplayer.fragmentSONGS";
    private StateSong mStateSong = StateSong.PAUSE;
    private MediaPlayer mMediaPlayer;
    private List<Song> mSongs;
    private Song mCurentSong;
    private Random mRandom = new Random();

    private ImageButton mImageButtonPlay;
    private ImageButton mImageButtonNext;
    private ImageButton mImageButtonPrevious;

    private ImageButton mImageButtonReapeat;

    private StateRepeat mStateRepeat = StateRepeat.ONE;

    private int mIntPauseSecond = 0;
    private TextView mTextViewTittle;


    public static PlayingMusicFragment newInstance(List<Song> songs) {
        PlayingMusicFragment fragment = new PlayingMusicFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_SONGS, (Serializable) songs);
        fragment.setArguments(args);
        return fragment;
    }

    public void updateList(List<Song> list) {
        mSongs = list;
        mMediaPlayer.reset();
        mIntPauseSecond = 0;
        mCurentSong = list.get(0);
        if (list.size() == 1) {
            startCurent();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSongs = (List<Song>) getArguments().getSerializable(KEY_SONGS);
        }
        if (mMediaPlayer == null && mSongs != null) {
            mMediaPlayer = new MediaPlayer();
            updateList(mSongs);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playing_music, container, false);
        findView(view);
        //startCurent();
        setOnClick();
        initUi();
        initRepeatUi();
        initPlayButtonImage();
        return view;
    }

    private void initUi() {
        initPlayButtonImage();
        mTextViewTittle.setText(mCurentSong.getStringTitle());
    }

    private void setOnClick() {
        mImageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSong(mSongs.indexOf(mCurentSong) + 1);
                startCurent();
            }
        });
        mImageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeStateSong();
            }
        });
        mImageButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSong(mSongs.indexOf(mCurentSong) - 1);
                startCurent();
            }
        });
        mImageButtonReapeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo
                changeSongReapeat();
            }
        });
    }

    //one - all - shafle
    private void changeSongReapeat() {
        switch (mStateRepeat) {
            case ALL: {
                mStateRepeat = StateRepeat.SHUFLE;
                break;
            }
            case ONE: {
                mStateRepeat = StateRepeat.ALL;
                break;
            }
            case SHUFLE: {
                mStateRepeat = StateRepeat.ONE;
                break;
            }
        }
        initRepeatUi();
    }

    private void initRepeatUi() {
        switch (mStateRepeat) {
            case ALL: {
                mImageButtonReapeat.setImageResource(R.drawable.ic_repeat_all);
                break;
            }
            case ONE: {
                mImageButtonReapeat.setImageResource(R.drawable.ic_repeat_one);
                break;
            }
            case SHUFLE: {
                mImageButtonReapeat.setImageResource(R.drawable.ic_shafel);
                break;
            }
        }
    }

    private void changeStateSong() {
        switch (mStateSong) {
            case PLAY: {
                mStateSong = StateSong.PAUSE;
                mIntPauseSecond = mMediaPlayer.getCurrentPosition();
                mMediaPlayer.stop();
                break;
            }
            case PAUSE: {
                startCurent();
                break;
            }
        }
        initPlayButtonImage();
    }

    private void initPlayButtonImage() {
        switch (mStateSong) {
            case PLAY: {
                mImageButtonPlay.setImageResource(R.drawable.ic_on_play);
                break;
            }
            case PAUSE: {
                mImageButtonPlay.setImageResource(R.drawable.ic_play_2);
                break;
            }
        }
    }

    private void startCurent() {
        initUi();
        mMediaPlayer.reset();
        try {
            mMediaPlayer.setDataSource(getActivity(), mCurentSong.getUri());
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            mMediaPlayer.seekTo(mIntPauseSecond);
            mStateSong = StateSong.PLAY;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findView(View view) {
        mImageButtonNext = view.findViewById(R.id.imageButton_play_next);
        mImageButtonPlay = view.findViewById(R.id.imageButtin_play_play);
        mImageButtonPrevious = view.findViewById(R.id.imageButton_play_previous);
        mImageButtonReapeat = view.findViewById(R.id.imageButton_play_shuffle);
        mTextViewTittle = view.findViewById(R.id.textView_play_tittle);
    }

    private void changeSong(int toUpdate) {
        switch (mStateRepeat) {
            case ALL: {
                toUpdate = mSongs.indexOf(mCurentSong);
                break;
            }
            case ONE: {
                //noting just play normal
                break;
            }
            case SHUFLE: {
                toUpdate = mRandom.nextInt(mSongs.size());
                break;
            }
        }
        toUpdate = toUpdate % mSongs.size();
        mIntPauseSecond = 0;
        mCurentSong = mSongs.get(toUpdate);
        initUi();
    }

    public enum StateSong {
        PLAY, PAUSE
    }

    public enum StateRepeat {
        ALL, ONE, SHUFLE
    }

    @Override
    public void onPause() {
        super.onPause();
        mMediaPlayer.reset();
    }
}
