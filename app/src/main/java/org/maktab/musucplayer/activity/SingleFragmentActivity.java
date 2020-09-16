package org.maktab.musucplayer.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.database.SongRepository;
import org.maktab.musucplayer.fragment.MainFragment;
import org.maktab.musucplayer.fragment.PlayingMusicFragment;
import org.maktab.musucplayer.fragment.lists.ListFragment;

public class SingleFragmentActivity extends AppCompatActivity {

    protected ListFragment.Callbacks mCallbacks;

    protected Fragment mOnlineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        mCallbacks = new ListFragment.Callbacks() {
            @Override
            public void itemCalled(ListFragment.States states, String item) {
                PlayingMusicFragment fragment = PlayingMusicFragment.newInstance(
                        states,
                        SongRepository.newInstance(getApplication()).getListSong(states, item)
                );
                mOnlineFragment = fragment;
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.main_fragment_place,
                        fragment
                ).commit();
            }
        };
        if (savedInstanceState == null) {
            Fragment fragment = MainFragment.newInstance(mCallbacks);
            mOnlineFragment = fragment;
            FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
            tr.add(R.id.main_fragment_place, fragment).commit();
        }

    }

    protected void setMainFragment() {
        Fragment fragment = MainFragment.newInstance(mCallbacks);
        mOnlineFragment = fragment;
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.main_fragment_place, fragment).commit();

    }

    @Override
    public void onBackPressed() {

        if (mOnlineFragment.getClass().equals(PlayingMusicFragment.class)) {
            setMainFragment();
        } else
            super.onBackPressed();
    }
}

