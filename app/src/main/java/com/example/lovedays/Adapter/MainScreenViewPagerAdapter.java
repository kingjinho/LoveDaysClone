package com.example.lovedays.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.lovedays.Screens.MainScreenAnniversaryList;
import com.example.lovedays.Screens.MainScreenCenterTab;
import com.example.lovedays.Screens.MainScreenSettings;

/**
 * Created by KING JINHO on 2019-07-29
 */
public class MainScreenViewPagerAdapter extends FragmentStatePagerAdapter {

    public static final String TAG = MainScreenViewPagerAdapter.class.getSimpleName();
    public static final int COUNT = 3;

    public MainScreenViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MainScreenAnniversaryList();
            case 1:
                return new MainScreenCenterTab();
            case 2:
                return new MainScreenSettings();
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return COUNT;
    }

}
