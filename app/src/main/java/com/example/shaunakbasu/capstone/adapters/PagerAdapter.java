package com.example.shaunakbasu.capstone.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.shaunakbasu.capstone.RoutineFragment;
import com.example.shaunakbasu.capstone.Stopwatch;

/**
 * Created by shaunak basu on 17-11-2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Stopwatch stopwatch = new Stopwatch();
                return stopwatch;
            case 1:
                RoutineFragment routine = new RoutineFragment();
                return routine;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
