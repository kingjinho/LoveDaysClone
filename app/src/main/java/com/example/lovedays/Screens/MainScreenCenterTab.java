package com.example.lovedays.Screens;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.lovedays.R;
import com.example.lovedays.Utils.Const;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by KING JINHO on 2019-07-24
 */
public class MainScreenCenterTab extends AbsFragment {

    public static final String TAG = MainScreenCenterTab.class.getSimpleName();
    public static final String ME = "ME";
    public static final String YOU = "YOU";

    private ProgressBar mProgressDate;
    private TextView mTvDateCount;


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
        ImageView ivMe = view.findViewById(R.id.iv_me);
        ImageView ivHer = view.findViewById(R.id.iv_her);
        TextView tvMyName = view.findViewById(R.id.tv_myName);
        TextView tvHerName = view.findViewById(R.id.tv_herName);
        ConstraintLayout layoutProfile1 = view.findViewById(R.id.layout_profile1);
        ConstraintLayout layoutProfile2 = view.findViewById(R.id.layout_profile2);

        SharedPreferences sharedPreferences = root.getSharedPreferences(Const.USER, Context.MODE_PRIVATE);
        Const.relationshipDate = sharedPreferences.getString(Const.RELATIONSHIP_START, "");
        Const.myName = sharedPreferences.getString(Const.MY_NAME, "");
        Const.yourName = sharedPreferences.getString(Const.HIS_HER_NAME, "");

        tvMyName.setText(Const.myName);
        tvHerName.setText(Const.yourName);
        setProgressBar();

        mProgressDate.setOnClickListener(v -> showBottomPopupMenu(false, null));
        layoutProfile1.setOnClickListener(v -> showBottomPopupMenu(true, ME));
        layoutProfile2.setOnClickListener(v -> showBottomPopupMenu(true, YOU));

        return view;
    }

    private void setProgressBar() {
        mProgressDate.setMax(Const.MAX);
        try {
            Date dateRelationshipStart = new SimpleDateFormat("yyyy/M/dd").parse(Const.relationshipDate);
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(dateRelationshipStart);
            Date today = new Date();
            Const.relationshipSince = (int) ((today.getTime() - dateRelationshipStart.getTime()) / (24 * 60 * 60 * 1000));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                mProgressDate.setProgress(Const.relationshipSince % Const.MAX, true);
            else
                mProgressDate.setProgress(Const.relationshipSince % Const.MAX);
            mTvDateCount.setText(root.getString(R.string.in_love_since, Const.relationshipSince));
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

    private void showBottomPopupMenu(boolean isProfile, @Nullable String type) {
        BottomNavigationDrawerFragment drawerFragment = new BottomNavigationDrawerFragment(root, isProfile, type);
        drawerFragment.show(root.getSupportFragmentManager(), drawerFragment.getTag());
    }

    public static class BottomNavigationDrawerFragment extends BottomSheetDialogFragment {
        private NavigationView mNavigation;
        private boolean isProfile;
        private Context mContext;
        private String type;

        public BottomNavigationDrawerFragment(Context context, boolean isProfile, @Nullable String type) {
            this.mContext = context;
            this.isProfile = isProfile;
            this.type = type;
        }


        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_bottomsheet, container, false);
            mNavigation = view.findViewById(R.id.navigation_view);
            mNavigation.inflateMenu(isProfile ? R.menu.menu_text_image_click : R.menu.menu_progressbar_click);
            mNavigation.setNavigationItemSelectedListener(menuItem -> {
                if (isProfile)
                    onNameImageClick(menuItem.getItemId());
                else
                    onProgressbarClick(menuItem.getItemId());
                return false;
            });
            return view;
        }

        private void onNameImageClick(int id) {
            switch (id) {
                case R.id.changeName:
                    CenterTabInnerFragment fragment = new CenterTabInnerFragment(Type.getEnum(type));
                    root.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_container, fragment, fragment.getTag())
                            .addToBackStack(CenterTabInnerFragment.TAG)
                            .commit();
                    break;
                case R.id.changeProfilePicture:
                    Toast.makeText(mContext, "change profile picture", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.deleteProfilePicture:
                    Toast.makeText(mContext, "delete profile picture", Toast.LENGTH_SHORT).show();
                    break;
            }
            this.dismiss();
        }

        private void onProgressbarClick(int id) {
            switch (id) {
                case R.id.changeDate:
                    Toast.makeText(mContext, "change Date", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.changeText:
                    Toast.makeText(mContext, "change text", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.share:
                    Toast.makeText(mContext, "share", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    }
}
