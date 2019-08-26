package com.example.lovedays.Screens;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.lovedays.Adapter.MainScreenViewPagerAdapter;
import com.example.lovedays.R;
import com.google.android.material.tabs.TabLayout;

/**
 * Created by KING JINHO on 2019-07-29
 */
public class MainScreenViewPagerGroup extends AbsFragment {

    public static final String TAG = MainScreenViewPagerGroup.class.getSimpleName();
    private MainScreenViewPagerAdapter mAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private View mView;

    private boolean isInitial = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (isInitial) {
            mView = inflater.inflate(R.layout.mainscreen, container, false);
            mTabLayout = mView.findViewById(R.id.tablayout);
            mViewPager = mView.findViewById(R.id.viewPager);
            mAdapter = new MainScreenViewPagerAdapter(root.getSupportFragmentManager());
            mViewPager.setOffscreenPageLimit(3);
            mTabLayout.setupWithViewPager(mViewPager);
            setViewPager();
            setupTabViews();
        }
        mViewPager.setCurrentItem(1, false);
        isInitial = false;
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void setViewPager() {
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setupTabViews() {
        mTabLayout.getTabAt(0).setIcon(R.mipmap.ic_setting);
        mTabLayout.getTabAt(1).setText(root.getString(R.string.app_name));
        mTabLayout.getTabAt(2).setIcon(R.mipmap.ic_anni);
    }
}
