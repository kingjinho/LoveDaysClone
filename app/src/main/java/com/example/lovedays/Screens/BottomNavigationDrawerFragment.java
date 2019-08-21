package com.example.lovedays.Screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lovedays.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;

/**
 * Created by KING JINHO on 2019-08-21
 */
public class BottomNavigationDrawerFragment extends BottomSheetDialogFragment {
    private NavigationView mNavigation;
    private boolean mType = false;

    public BottomNavigationDrawerFragment(Type type){
        mType = type.isProfile();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottomsheet, container, false);
        mNavigation = view.findViewById(R.id.navigation_view);
        mNavigation.inflateMenu(mType? R.menu.menu_progressbar_click: R.menu.menu_text_image_click);
        return view;
    }
}

enum Type {
    PROFILE(true),
    PICTURE(false);

    private boolean isProfile;

    Type(boolean isProfile) {
        this.isProfile = isProfile;
    }

    public boolean isProfile() {
        return isProfile;
    }
}

