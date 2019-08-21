package com.example.lovedays.Screens;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.lovedays.R;
import com.example.lovedays.Utils.Const;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by KING JINHO on 2019-07-24
 */
public class MainScreenCenterTab extends AbsFragment {

    public static final String TAG = MainScreenCenterTab.class.getSimpleName();
    private ProgressBar mProgressDate;
    private TextView mTvDateCount;
    private String mRelationshopStart;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainscreen_centertab, container, false);
        mProgressDate = view.findViewById(R.id.progress_date);
        mTvDateCount = view.findViewById(R.id.dateCount);
        SharedPreferences sharedPreferences = root.getSharedPreferences(Const.USER, Context.MODE_PRIVATE);
        mRelationshopStart = sharedPreferences.getString(Const.RELATIONSHIP_START, "");

        ImageView ivMe = view.findViewById(R.id.iv_me);
        ImageView ivHer = view.findViewById(R.id.iv_her);
        TextView tvMyName = view.findViewById(R.id.tv_myName);
        TextView tvHerName =  view.findViewById(R.id.tv_herName);

        ConstraintLayout layoutProfile1 = view.findViewById(R.id.layout_profile1);
        ConstraintLayout layoutProfile2 = view.findViewById(R.id.layout_profile2);
        setProgressBar();
        mProgressDate.setOnClickListener(v -> showBottomPopupMenu(v));
        layoutProfile1.setOnClickListener(v-> showBottomPopupMenu(v));
        layoutProfile2.setOnClickListener(v-> showBottomPopupMenu(v));

        return view;
    }

    private void setProgressBar() {
        mProgressDate.setMax(Const.MAX);
        try {
            Date dateRelationshipStart = new SimpleDateFormat("yyyy/M/dd").parse(mRelationshopStart);
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(dateRelationshipStart);
            Date today = new Date();
            int dateCount = (int) ((today.getTime() - dateRelationshipStart.getTime()) / (24 * 60 * 60 * 1000));
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                mProgressDate.setProgress(dateCount % Const.MAX, true);
            else
                mProgressDate.setProgress(dateCount % Const.MAX);
            mTvDateCount.setText(root.getString(R.string.in_love_since, dateCount));
        } catch (Exception e) {
            Toast.makeText(root, R.string.error, Toast.LENGTH_SHORT).show();
        }
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

    private void showBottomPopupMenu(View view) {
        BottomNavigationDrawerFragment drawerFragment;
        if(view instanceof ProgressBar) {
            drawerFragment = new BottomNavigationDrawerFragment(Type.PROFILE);
        } else drawerFragment = new BottomNavigationDrawerFragment(Type.PICTURE);
        drawerFragment.show(root.getSupportFragmentManager(), drawerFragment.getTag());
    }
}
