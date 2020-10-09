package org.maktab.musucplayer.utils;

import java.util.ArrayList;
import java.util.Random;

public class Ordering {
    private int mSize;
    private int mIntRepeat;
    private boolean mBooleanOnrepeat = false;
    private int mIntCurrent;
    private ArrayList<Integer> mList;


    public Ordering(int mSize) {
        this.mSize = mSize;
        mIntCurrent = 0;
        mList = initOrderRespectivly(mSize);
    }

    public void inableShuffle() {
        initOrderShuffle(mSize, mIntCurrent);
    }

    public void disableShuffle() {
        mList = initOrderRespectivly(mSize);
    }

    public void inableRepeat() {
        mBooleanOnrepeat = true;
    }

    public void disableRepeat() {
        mBooleanOnrepeat = false;
    }

    public int getCurent() {
        return mList.get(mIntCurrent);
    }

    public void setCurent(int curent) {
        mIntCurrent = mList.indexOf(curent);
    }

    public int getPrevios() {
        if (mBooleanOnrepeat) {
            return mList.get(mIntCurrent);
        } else if (mIntCurrent == 0) {
            mIntCurrent = mList.size() - 1;
        } else {
            mIntCurrent--;
        }
        return mList.get(mIntCurrent);
    }

    public int getNext() {
        if (mBooleanOnrepeat) {
            return mList.get(mIntCurrent);
        } else if (mIntCurrent == mList.size() - 1) {
            mIntCurrent = 0;
        } else {
            mIntCurrent++;
        }
        return mList.get(mIntCurrent);
    }

    private ArrayList<Integer> initOrderRespectivly(int size) {
        ArrayList<Integer> temp = new ArrayList();
        for (int i = 0; i < size; i++) {
            temp.add(i);
        }
        return temp;
    }

    private void initOrderShuffle(final int mSize, final int current) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Random rn = new Random();
                ArrayList<Integer> temp = initOrderRespectivly(mSize);
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
                //for dont missing current to position
                int misplace = ans.get(current);
                int misplaceIndex = ans.indexOf(current);
                ans.set(current, current);
                ans.set(misplaceIndex, misplace);
                mList = ans;
            }
        }).start();

    }
}
