package org.maktab.musucplayer.ui.main.fragment;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.viewpager2.widget.ViewPager2;

import org.maktab.musucplayer.adapter.ViewPagerAdapter;

public class MainFragmentViewModel extends ViewModel {
    private ViewPagerAdapter mViewPagerAdapter;
    private MutableLiveData<String> mFieldUpBar = new MutableLiveData<>();
    public static final String[] mFragmentsName = new String[]{
            "music",
            "artist",
            "album"};

    public MainFragmentViewModel() {
    }


    public void setCallbackRegestery(final ViewPager2 viewPagerLists) {
        viewPagerLists.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mFieldUpBar.setValue((mFragmentsName[position]));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);

            }
        });
    }

    public MutableLiveData<String> getFieldUpBar() {
        return mFieldUpBar;
    }

    public void setupPager(MainFragment mainFragment, ViewPager2 viewPagerLists, Fragment[] fragmentsList) {
        //todo find solution to dont send fragment contex
        if (mViewPagerAdapter == null) {
            mViewPagerAdapter = new ViewPagerAdapter(mainFragment.getActivity(), fragmentsList);
            viewPagerLists.setAdapter(mViewPagerAdapter);
        }

    }
}
