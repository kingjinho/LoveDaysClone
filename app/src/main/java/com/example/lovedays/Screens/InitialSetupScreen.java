package com.example.lovedays.Screens;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.lovedays.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by KING JINHO on 2019-07-24
 */
public class InitialSetupScreen extends AbsFragment {

    public static final String TAG = InitialSetupScreen.class.getSimpleName();

    private Button mBtnClear;
    private Button mBtnStart;
    private TextView mTvRelationshipDate;
    private EditText mEtMyName;
    private EditText mEtHisHerName;
    private EditText mEtMobileNumber;
    private ConstraintLayout mLayoutFragment;
    private View mViewInflated;
    private Date today = new Date();
    private String mRelationshipSince;

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
        super.onCreateView(inflater, container, savedInstanceState);//안하면 mDefaultView null.
        mViewInflated = inflater.inflate(R.layout.initialsetupscreen, container, false);
        mLayoutFragment = mDefaultView.findViewById(R.id.fragment_container);
        final Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DATE);
        mEtMyName = mViewInflated.findViewById(R.id.etMyName);
        mEtHisHerName = mViewInflated.findViewById(R.id.etTheirName);
        mEtMobileNumber = mViewInflated.findViewById(R.id.etMobileNumber);
        mBtnStart = mViewInflated.findViewById(R.id.btnStart);
        mBtnClear = mViewInflated.findViewById(R.id.btnClear);
        mTvRelationshipDate = mViewInflated.findViewById(R.id.tvDateSelect);
        mTvRelationshipDate.setText(new SimpleDateFormat("yyyy/M/dd").format(today));
        mTvRelationshipDate.setOnClickListener(v -> {
            DatePickerDialog dialog = new DatePickerDialog(root, R.style.DialogTheme, (datePicker, yearSelected, monthSelected, dateSelected) -> {
                mRelationshipSince = yearSelected + "/"
                        + (monthSelected + 1) + "/"
                        + dateSelected;
                mTvRelationshipDate.setText(mRelationshipSince);
            }, year, month, dayOfMonth);
            dialog.show();
        });

        mBtnStart.setOnClickListener(v -> {
            root.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new MainScreenViewPagerGroup(), MainScreenViewPagerGroup.TAG )
                    .commit();
        });

        mBtnClear.setOnClickListener(v -> {
            clear();
        });

        mLayoutFragment.addView(mViewInflated);
        return mDefaultView;
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
        mLayoutFragment.removeView(mViewInflated);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void clear() {
        mEtMyName.setText("");
        mEtHisHerName.setText("");
        mEtMobileNumber.setText("");
        setToday();
    }

    private void setToday(){
        mTvRelationshipDate.setText(new SimpleDateFormat("yyyy/M/dd").format(today));
    }
}
