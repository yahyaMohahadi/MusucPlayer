package org.maktab.musucplayer.utils;

import android.content.Context;
import android.media.MediaPlayer;

import org.maktab.musucplayer.model.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Music {
    private Context mContext;
    private List<Song> mSongList;
    private List<Integer> mOrder = new ArrayList<>();
    private static Music sInstance;
    private MediaPlayer mMediaPlayer;
    private int mIntegerPersentPlayed;
    private int mIntegerMusicTotal;
    private int mIntegerCurentSong;
    private StateRepeat mStateRepeat;
    private StatePlay mStatePlay;
    private Thread mThreadTime;

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

    private static void initFirst(List<Song> songs)  {
        if (sInstance.mMediaPlayer != null) {
            sInstance.mMediaPlayer.reset();
        }
        sInstance.mSongList = songs;
        sInstance.initStateRepeat(StateRepeat.RESPECTIVLY, null);
        sInstance.mMediaPlayer = new MediaPlayer();
        sInstance.prepare(0);
        sInstance.mStatePlay = StatePlay.PAUSE;
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
        mThreadTime.start();
        mMediaPlayer.seekTo((int)(Float.valueOf((mIntegerPersentPlayed*mIntegerMusicTotal))/100));
        mMediaPlayer.start();
    }

    private void puse() {
        mMediaPlayer.pause();
        mThreadTime.interrupt();
    }

    /**
     * it in the thread for beter performance
     *
     * @param stateRepeat you can inter it if you want to repeat track
     *                    could be null in not repeat
     * @param repeat
     */
    public void initStateRepeat(final StateRepeat stateRepeat, final Integer repeat) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                switch (stateRepeat) {
                    case SHUFFLE: {
                        mOrder = initOrderShuffle(mSongList.size());
                        break;
                    }
                    case REPEAT: {
                        mOrder = initOrderRepeat(repeat);
                        break;
                    }
                    case RESPECTIVLY: {
                        mOrder = initOrderRepeat(mSongList.size());
                        break;
                    }
                }
            }
        }).start();
        mStateRepeat = stateRepeat;

    }

    public void direction(Direction direction) throws IOException {
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
        if (mIntegerCurentSong == mOrder.get(0)) {
            mIntegerCurentSong = mOrder.get(mOrder.size() - 1);
        } else {
            mIntegerCurentSong = mOrder.get(mIntegerCurentSong - 1);
        }
        prepare(mIntegerCurentSong);

    }

    private void prepare(Integer integerCurentSong)  {
        mIntegerPersentPlayed = 0;
        try {
            mMediaPlayer.setDataSource(mContext, mSongList.get(integerCurentSong).getUri());
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mIntegerMusicTotal = mMediaPlayer.getDuration();
        runTimeThread();

    }

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

    private void next() throws IOException {
        if (mIntegerCurentSong == mOrder.get(mOrder.size() - 1)) {
            mIntegerCurentSong = mOrder.get(0);
        } else {
            mIntegerCurentSong = mOrder.get(mIntegerCurentSong + 1);
        }
        prepare(mIntegerCurentSong);
    }

    private static ArrayList<Integer> initOrderRespectivly(int size) {
        ArrayList<Integer> temp = new ArrayList();
        for (int i = 0; i < size; i++) {
            temp.add(i);
        }
        return temp;
    }

    private static ArrayList<Integer> initOrderShuffle(int size) {
        Random rn = new Random();
        ArrayList<Integer> temp = initOrderRespectivly(size);
        ArrayList<Integer> ans = new ArrayList<>();
        while (true) {
            int getRndom = temp.get(rn.nextInt(temp.size() - 1));
            ans.add(getRndom);
            temp.remove((Integer) getRndom);
            if (temp.size() == 1) {
                ans.add(temp.get(0));
                break;
            }
        }
        return ans;
    }

    private static ArrayList<Integer> initOrderRepeat(int repeat) {
        ArrayList<Integer> temp = new ArrayList(Arrays.asList(repeat));
        return temp;
    }

    private static void deatach() {
        sInstance.mMediaPlayer.reset();
        sInstance = null;
        System.gc();
    }


    public enum StatePlay {
        PLAY, PAUSE
    }

    public enum StateRepeat {
        REPEAT, RESPECTIVLY, SHUFFLE
    }

    public enum Direction {
        NEWXT, PREVIOUS
    }

}

