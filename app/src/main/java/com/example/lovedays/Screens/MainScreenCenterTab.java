package com.example.lovedays.Screens;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lovedays.R;
import com.example.lovedays.Utils.Const;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by KING JINHO on 2019-07-24
 */
public class MainScreenCenterTab extends AbsFragment {

    public static final String TAG = MainScreenCenterTab.class.getSimpleName();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainscreen_centertab, container, false);
        ProgressBar progress_date = view.findViewById(R.id.progress_date);
        TextView tvDateCount = view.findViewById(R.id.dateCount);
        SharedPreferences sharedPreferences = root.getSharedPreferences(Const.USER, Context.MODE_PRIVATE);
        String relationshipStart = sharedPreferences.getString(Const.RELATIONSHIP_START, "");

        progress_date.setMax(Const.MAX);
        try {
            Date dateRelationshipStart = new SimpleDateFormat("yyyy/M/dd").parse(relationshipStart);
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(dateRelationshipStart);
            Date today = new Date();
            int dateCount = (int) ((today.getTime() - dateRelationshipStart.getTime()) / (24 * 60 * 60 * 1000));
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                progress_date.setProgress(dateCount % 1000, true);
            else
                progress_date.setProgress(dateCount % 1000);
            tvDateCount.setText(root.getString(R.string.in_love_since, dateCount));
        } catch (Exception e) {
            Toast.makeText(root, R.string.error, Toast.LENGTH_SHORT).show();
        }
        return view;
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
}
