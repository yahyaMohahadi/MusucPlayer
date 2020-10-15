package org.maktab.musucplayer.view_model.orderingFragment;

import android.util.Log;

import androidx.fragment.app.FragmentManager;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.view.fragment.DitailMusicFragment;
import org.maktab.musucplayer.view.fragment.MainFragment;
import org.maktab.musucplayer.view.fragment.PlayingMusicFragment;

public class ViewModelOrdering {

    private StateOnline mStateOnline;
    private FragmentManager mManager;
    private MainFragment mFragmentMian;
    private DitailMusicFragment mFragmentDitails;
    private PlayingMusicFragment mFragmentPlay;
    private boolean mBooleanllFragmentsSet = true;

    public StateOnline getStateOnline() {
        return mStateOnline;
    }

    public static ViewModelOrdering newInstance(FragmentManager manager) {
        ViewModelOrdering viewModel = new ViewModelOrdering(manager);
        return viewModel;
    }

    private ViewModelOrdering(FragmentManager manager) {
        mManager = manager;
        initFragments();
    }

    private void startHandler() {
        initFragments();
        /*Handeler<ViewModelOrdering> handler = new Handeler<ViewModelOrdering>(new Handler());
        handler.setListener(new Handeler.HandlerAns() {
            @Override
            public void onCopleate(boolean ans) {
                mBooleanllFragmentsSet = true;
            }
        });
        handler.queueThumbnailMessage(this);*/
    }


    public void setupDetal() {
        if (mFragmentDitails != null) {
            mStateOnline = StateOnline.DITAIL;
            mManager.beginTransaction().replace(R.id.main_fragment_place, mFragmentDitails).commit();
            mManager.beginTransaction().remove(mFragmentPlay).commit();
        } else {
            initFragments();
        }
    }

    public void setupMain() {
        if (mFragmentMian != null) {
            mStateOnline = StateOnline.MAIN;
            mManager.beginTransaction().replace(R.id.main_fragment_place, mFragmentMian).commit();
            mManager.beginTransaction().replace(R.id.frgment_playing_place, mFragmentPlay).commit();
        } else {
            initFragments();
            setupMain();
        }
    }

    public void initFragments() {
        mFragmentMian = MainFragment.newInstance();
        mFragmentDitails = DitailMusicFragment.newInstance();
        mFragmentPlay = PlayingMusicFragment.newInstance();
    }

    public enum StateOnline {
        DITAIL, MAIN
    }
}
