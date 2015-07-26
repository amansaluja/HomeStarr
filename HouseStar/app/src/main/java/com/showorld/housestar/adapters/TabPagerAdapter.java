package com.showorld.housestar.adapters;

/**
 * Created by Aman on 04-07-2015.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.showorld.housestar.fragments.TabFragment;


public class TabPagerAdapter extends FragmentStatePagerAdapter {
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                //Fragement for Android Tab
                return new TabFragment();
            case 1:
                //Fragment for Ios Tab
                return new TabFragment();
            case 2:
                //Fragment for Windows Tab
                return new TabFragment();
            case 3:
                //Fragment for Windows Tab
                return new TabFragment();
        }
        return null;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 4; //No of Tabs
    }

}