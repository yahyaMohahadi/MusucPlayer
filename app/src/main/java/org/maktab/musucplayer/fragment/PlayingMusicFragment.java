package org.maktab.musucplayer.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.adapter.MusicListAdapter;
import org.maktab.musucplayer.model.Song;
import org.maktab.musucplayer.utils.Music;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;


public class PlayingMusicFragment extends Fragment {


    public static final String KEY_SONGS = "org.maktab.musucplayer.fragmentSONGS";
    public static final int LIMIT_TITTLE_PLAY_BAR = 30;
    private List<Song> mSongs;
    private Music mMusic;
    private ImageButton mImageButtonNext;
    private ImageButton mImageButtonPlay;
    private TextView mTextViewArtist;
    private ImageView mImageViewMusicCover;

    public static PlayingMusicFragment newInstance(List<Song> songs) {
        PlayingMusicFragment fragment = new PlayingMusicFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_SONGS, (Serializable) songs);
        fragment.setArguments(args);
        return fragment;
    }

    public void updateList(List<Song> list) {
        mSongs = list;
        mMusic.setSongList(list);
        if (list.size() == 1) {
            mMusic.initStatePlay(Music.StatePlay.PLAY);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSongs = (List<Song>) getArguments().getSerializable(KEY_SONGS);
            mMusic = mMusic.newInstance(this.getActivity(), mSongs);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playing_music, container, false);
        findView(view);
        setOnClick();
        initUi();

        initPlayButtonImage();
        return view;
    }


    private void setOnClick() {


        mImageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mMusic.initDirection(Music.Direction.NEWXT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                initUi();
            }
        });
        mImageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeStateSong();
                initUi();
            }
        });
    }


    private void changeStateSong() {
        switch (mMusic.getStatePlay()) {
            case PLAY: {
                mMusic.initStatePlay(Music.StatePlay.PAUSE);
                break;
            }
            case PAUSE: {
                mMusic.initStatePlay(Music.StatePlay.PLAY);
                break;
            }
        }
        initPlayButtonImage();
    }

    private void initPlayButtonImage() {
        switch (mMusic.getStatePlay()) {
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

    private void findView(View view) {
        mImageButtonNext = view.findViewById(R.id.imageButton_play_next);
        mImageButtonPlay = view.findViewById(R.id.imageButtin_play_play);
        mTextViewTittle = view.findViewById(R.id.textView_play_tittle);
        mTextViewArtist = view.findViewById(R.id.textView_playing_fragment_artist);
        mImageViewMusicCover = view.findViewById(R.id.imageView_play_fragment);
    }

    @Override
    public void onPause() {
        super.onPause();
        mMusic.deatach();
    }


    private TextView mTextViewTittle;

    private void initUi() {
        initPlayButtonImage();
        mTextViewTittle.setText(MusicListAdapter.limitString(mMusic.getCurentSong().getStringTitle(), LIMIT_TITTLE_PLAY_BAR));
        mTextViewArtist.setText(MusicListAdapter.limitString(mMusic.getCurentSong().getStringArtist(), LIMIT_TITTLE_PLAY_BAR));
        mImageViewMusicCover.setImageBitmap(mMusic.getCurentSong().getImageMusicSize(getActivity()));
    }

    /*        mImageButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mMusic.initDirection(Music.Direction.PREVIOUS);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                initUi();
            }
        });
        mImageButtonShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeShuffle();
            }
        });*/
        /*initRepeat();
        initShuffle();*/
    /*
        private void initShuffle() {
            switch (mMusic.getStateShuffle()) {
                case SHUFFLE: {
                    mImageButtonShuffle.setImageResource(R.drawable.ic_shafel);
                    break;
                }
                case RESPECTIVLY: {
                    mImageButtonShuffle.setImageResource(R.drawable.ic_no_shuffle);
                    break;
                }
            }
        }
    */
    //todo seperate shuffle from tepeat
    /* private ImageButton mImageButtonPrevious;
     private ImageButton mImageButtonShuffle;*/
    //seekbar
    /* private SeekBar mSeekBar;

         mSeekBar.setMax(100);
         mSeekBar.setMin(0);
         setSeekbarCallbacks();
     private void setSeekBarProgtess() {
         mSeekBar.setProgress((int) (Float.valueOf(mMediaPlayer.getCurrentPosition()) / Float.valueOf(mIntCurentDuration) * 100));
     }

     private void startRunSeekbar() {

         new java.util.Timer().schedule(
                 new java.util.TimerTask() {
                     @Override
                     public void run() {
                         if (mStateSong == StateSong.PLAY) {
                             setSeekBarProgtess();
                             checkFinish();

                             startRunSeekbar();
                         }
                     }
                 },
                 DELAY_TIME_SEEK_BAR
         );
     }

     protected void setSeekbarCallbacks() {
         mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

             }

             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {

             }

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {
                 mSeekBar.setProgress(seekBar.getProgress());
                 switch (mStateSong) {
                     case PAUSE: {
                         mIntPauseSecond = (seekBar.getProgress() * mIntCurentDuration) / 100;
                         break;
                     }
                     case PLAY: {
                         mMediaPlayer.seekTo((seekBar.getProgress() * mIntCurentDuration) / 100);
                         break;
                     }
                 }
             }
         });
     }*/
    /*      mImageButtonRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeReapeat();
            }
        });*/
    /*    private void changeReapeat() {
        switch (mMusic.getStateRepeat()) {
            case NORMAL: {
                mImageButtonRepeat.setImageResource(R.drawable.ic_repeat);
                mMusic.initStateRepeat(Music.StateRepeat.REPEAT);
                break;
            }
            case REPEAT: {
                mImageButtonRepeat.setImageResource(R.drawable.ic_no_repeat);
                mMusic.initStateRepeat(Music.StateRepeat.NORMAL);
                break;
            }
        }
    }*/
    /*    private void initRepeat() {
        switch (mMusic.getStateRepeat()) {
            case REPEAT: {
                mImageButtonShuffle.setImageResource(R.drawable.ic_repeat_all);
                break;
            }
            case NORMAL: {
                mImageButtonShuffle.setImageResource(R.drawable.ic_no_repeat);
                break;
            }
        }
    }*/

    //private ImageButton mImageButtonRepeat;

}
