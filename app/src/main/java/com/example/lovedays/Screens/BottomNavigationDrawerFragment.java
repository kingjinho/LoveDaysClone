package com.example.lovedays.Screens;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lovedays.MainActivity;
import com.example.lovedays.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;

/**
 * Created by KING JINHO on 2019-08-21
 */
public class BottomNavigationDrawerFragment extends BottomSheetDialogFragment {
    private NavigationView mNavigation;
    private boolean isProfile = false;
    private Context mContext;

    public BottomNavigationDrawerFragment(Context context, boolean isProfile) {
        this.mContext = context;
        this.isProfile = isProfile;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottomsheet, container, false);
        mNavigation = view.findViewById(R.id.navigation_view);
        mNavigation.inflateMenu(isProfile ? R.menu.menu_progressbar_click : R.menu.menu_text_image_click);
        mNavigation.setNavigationItemSelectedListener(menuItem -> {
            Toast.makeText(mContext, "hello", Toast.LENGTH_SHORT).show();
            return false;
        });
        return view;
    }
}



