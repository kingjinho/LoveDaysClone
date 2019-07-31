package com.example.lovedays.Screens;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.util.StringUtil;

import com.example.lovedays.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by KING JINHO on 2019-07-24
 */
public class InitialSetupScreen extends AbsFragment {

    public static final String TAG = InitialSetupScreen.class.getSimpleName();
    public static final String HISHER_NAME = "HISHER_NAME";
    public static final String MY_NAME = "MY_NAME";
    public static final String RELATIONSHIP_SINCE = "RELATIONSHIP_SINCE";

    private Button mBtnClear;
    private Button mBtnStart;
    private TextView mTvRelationshipDate;
    private EditText mEtMyName;
    private EditText mEtHisHerName;
    private ConstraintLayout mLayoutFragment;
    private View mViewInflated;
    private Date today = new Date();
    private String mRelationshipSince = "";

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
        mBtnStart = mViewInflated.findViewById(R.id.btnStart);
        mBtnClear = mViewInflated.findViewById(R.id.btnClear);
        mTvRelationshipDate = mViewInflated.findViewById(R.id.tvDateSelect);
        if(savedInstanceState != null && savedInstanceState.getString(MY_NAME) != null)
            setDataFromSaveInstance(savedInstanceState);

        mTvRelationshipDate.setPaintFlags(mTvRelationshipDate.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mTvRelationshipDate.setText(new SimpleDateFormat("yyyy/M/dd").format(today));
        mRelationshipSince = new SimpleDateFormat("yyyy/M/dd").format(today);
        mTvRelationshipDate.setOnClickListener(v -> {
            DatePickerDialog dialog = new DatePickerDialog(root, R.style.DialogTheme, (datePicker, yearSelected, monthSelected, dateSelected) -> {
                mRelationshipSince = yearSelected + "/"
                        + (monthSelected + 1) + "/"
                        + dateSelected;
                if (dateValidation(yearSelected, monthSelected, dateSelected))
                    mTvRelationshipDate.setText(mRelationshipSince);
                else
                    Toast.makeText(root, R.string.date_cannot_be_later_than_today, Toast.LENGTH_SHORT).show();
            }, year, month, dayOfMonth);
            dialog.show();
        });

        mBtnStart.setOnClickListener(v -> {
            if (inputValidation()) {
                saveInfo();
                root.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, new MainScreenViewPagerGroup(), MainScreenViewPagerGroup.TAG)
                        .commit();
            }
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
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(!mEtHisHerName.getText().toString().equals(""))
            outState.putString(HISHER_NAME, mEtHisHerName.getText().toString());

        if (!mEtMyName.getText().toString().equals(""))
            outState.putString(MY_NAME, mEtMyName.getText().toString());

        if (!mRelationshipSince.equals(""))
            outState.putString(RELATIONSHIP_SINCE, mRelationshipSince);
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

    /*@Override
    public void onConfigurationChanged(Configuration newConfig) {
        if(newConfig == )
    }*/

    private void clear() {
        mEtMyName.setText("");
        mEtHisHerName.setText("");
        setToday();
    }

    private void setToday() {
        mTvRelationshipDate.setText(new SimpleDateFormat("yyyy/M/dd").format(today));
    }

    private boolean dateValidation(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date);
        if (calendar.getTime().after(today))
            return false;
        else
            return true;
    }

    private boolean inputValidation() {
        if (mEtMyName.getText().toString().equals("")) {
            Toast.makeText(root, R.string.msg_empty_your_name, Toast.LENGTH_SHORT).show();
            return false;
        } else if (mEtHisHerName.getText().toString().equals("")) {
            Toast.makeText(root, R.string.msg_empty_their_name, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void saveInfo() {

    }

    private void setDataFromSaveInstance(Bundle saveInstance) {
        if(!saveInstance.getString(HISHER_NAME).equals(""))
            mEtHisHerName.setText(saveInstance.getString(HISHER_NAME));
        if (!saveInstance.getString(MY_NAME).equals(""))
            mEtMyName.setText(saveInstance.getString(MY_NAME));
        if(!mRelationshipSince.equals("")){
            mTvRelationshipDate.setText(mRelationshipSince);
        }
    }

}
