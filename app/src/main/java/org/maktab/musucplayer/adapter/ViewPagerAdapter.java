package org.maktab.musucplayer.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private Fragment[] mFragmentList;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, Fragment[] fragments) {
        super(fragmentActivity);
        mFragmentList = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragmentList[position];
    }

    @Override
    public int getItemCount() {
        return mFragmentList.length;
    }
}
