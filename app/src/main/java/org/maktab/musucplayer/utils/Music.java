package org.maktab.musucplayer.utils;

import android.content.Context;
import android.media.MediaPlayer;

import org.maktab.musucplayer.model.Song;

import java.io.IOException;
import java.util.List;

public class Music {
    private Context mContext;
    private List<Song> mSongList;
    private Ordering mOrdering;
    private static Music sInstance;
    private MediaPlayer mMediaPlayer;
    private int mIntegerPersentPlayed;
    private int mIntegerMusicTotal;

    private StateShuffle mStateShuffle;
    private StateRepeat mStateRepeat;
    private StatePlay mStatePlay;
//    private Thread mThreadTime;
    //TODO THRAD AND CALLBACKS FOR SEEKBAR

    private Music() {
    }

    public static Music newInstance(Context context, List<Song> songs) {
        if (sInstance == null) {
            sInstance = new Music();
            sInstance.mContext = context.getApplicationContext();
            initFirst(songs);
        }
        return sInstance;
    }

    private static void initFirst(List<Song> songs) {
        if (sInstance.mMediaPlayer != null) {
            sInstance.mMediaPlayer.reset();
        }
        sInstance.mOrdering = new Ordering(songs.size());
        sInstance.mSongList = songs;
        sInstance.initStateShuffle(StateShuffle.RESPECTIVLY);
        sInstance.initStateRepeat(StateRepeat.NORMAL);
        sInstance.mMediaPlayer = new MediaPlayer();
        sInstance.prepare(0);
        sInstance.mStatePlay = StatePlay.PAUSE;
    }

    public void setSongList(List<Song> songList) {
        mSongList = songList;
        initFirst(songList);
    }

    public void initStatePlay(StatePlay statePlay) {
        switch (mStatePlay) {
            case PLAY: {
                if (statePlay == StatePlay.PAUSE) {
                    puse();
                }
                break;
            }
            case PAUSE: {
                if (statePlay == StatePlay.PLAY) {
                    play();
                }
                break;
            }
        }
    }

    private void play() {
        mStatePlay = StatePlay.PLAY;
//        mThreadTime.start();
        mMediaPlayer.seekTo(mIntegerPersentPlayed);
        mMediaPlayer.start();
    }

    private void puse() {
        mIntegerPersentPlayed = mMediaPlayer.getCurrentPosition();
        mStatePlay = StatePlay.PAUSE;
        mMediaPlayer.pause();
        //       mThreadTime.interrupt();
    }

    public void initStateShuffle(final StateShuffle stateRepeat) {
        switch (stateRepeat) {
            case SHUFFLE: {
                mOrdering.inableShuffle();
                break;
            }
            case RESPECTIVLY: {
                mOrdering.disableShuffle();
                break;
            }
        }
        mStateShuffle = stateRepeat;
    }

    public void initStateRepeat(final StateRepeat stateRepeat) {
        switch (stateRepeat) {
            case REPEAT: {
                mOrdering.inableRepeat();
                break;
            }
            case NORMAL: {
                mOrdering.disableRepeat();
                break;
            }
        }
        mStateRepeat = stateRepeat;
    }

    public void initDirection(Direction direction) throws IOException {
        switch (direction) {
            case NEWXT: {
                next();
                break;
            }
            case PREVIOUS: {
                previous();
                break;
            }
        }
    }

    private void previous() throws IOException {
        mOrdering.getPrevios();

        prepare(mOrdering.getCurent());
        if (mStatePlay == StatePlay.PLAY) {
            play();
        }
    }

    private void prepare(Integer integerCurentSong) {
        mIntegerPersentPlayed = 0;
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(mContext, mSongList.get(mOrdering.getCurent()).getUri());
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mIntegerMusicTotal = mMediaPlayer.getDuration();
        //runTimeThread();

    }

/*
    private void runTimeThread() {
        mThreadTime =
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            new java.util.Timer().schedule(
                                    new java.util.TimerTask() {
                                        @Override
                                        public void run() {
                                            int persent = (int)
                                                    (100 * (Float.valueOf(
                                                            mMediaPlayer.getCurrentPosition())
                                                            / Float.valueOf(
                                                            mIntegerMusicTotal)));
                                            mIntegerPersentPlayed = persent;
                                        }
                                    },
                                    500
                            );
                        }
                    }
                });
    }
*/

    private void next() throws IOException {
        mOrdering.getNext();
        prepare(mOrdering.getCurent());
        if (mStatePlay == StatePlay.PLAY) {
            play();
        }
    }


    public int getIntegerPersentPlayed() {
        return mIntegerPersentPlayed;
    }

    public int getIntegerMusicTotal() {
        return mIntegerMusicTotal;
    }

    public StateRepeat getStateRepeat() {
        return mStateRepeat;
    }

    public Song getCurentSong() {
        return mSongList.get(mOrdering.getCurent());
    }

    public StateShuffle getStateShuffle() {
        return mStateShuffle;
    }

    public StatePlay getStatePlay() {
        return mStatePlay;
    }

    public void deatach() {
        sInstance.mMediaPlayer.reset();
        sInstance = null;
        System.gc();
    }


    public enum StatePlay {
        PLAY, PAUSE
    }

    public enum StateShuffle {
        RESPECTIVLY, SHUFFLE
    }

    public enum StateRepeat {
        REPEAT, NORMAL
    }

    public enum Direction {
        NEWXT, PREVIOUS
    }

}

