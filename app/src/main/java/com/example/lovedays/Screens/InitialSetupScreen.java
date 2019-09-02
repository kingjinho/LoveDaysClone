package com.example.lovedays.Screens;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
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

import com.example.lovedays.R;
import com.example.lovedays.Utils.Const;

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
        final Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DATE);
        mEtMyName = mViewInflated.findViewById(R.id.etMyName);
        mEtHisHerName = mViewInflated.findViewById(R.id.etTheirName);
        mBtnStart = mViewInflated.findViewById(R.id.btnStart);
        mBtnClear = mViewInflated.findViewById(R.id.btnClear);
        mTvRelationshipDate = mViewInflated.findViewById(R.id.tvDateSelect);
        if (savedInstanceState != null && savedInstanceState.getString(Const.MY_NAME) != null)
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

        mBtnClear.setOnClickListener(v -> clear());

        return mViewInflated;
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
        if (!mEtHisHerName.getText().toString().equals(""))
            outState.putString(Const.HIS_HER_NAME, mEtHisHerName.getText().toString());

        if (!mEtMyName.getText().toString().equals(""))
            outState.putString(Const.MY_NAME, mEtMyName.getText().toString());

        if (!mRelationshipSince.equals(""))
            outState.putString(Const.RELATIONSHIP_START, mRelationshipSince);
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
        SharedPreferences sharedPreferences = root.getSharedPreferences(Const.USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Const.MY_NAME, mEtMyName.getText().toString());
        editor.putString(Const.HIS_HER_NAME, mEtHisHerName.getText().toString());
        editor.putString(Const.RELATIONSHIP_START, mTvRelationshipDate.getText().toString());
        editor.putBoolean(Const.IS_REGISTERED, true);

        editor.commit();
    }

    private void setDataFromSaveInstance(Bundle saveInstance) {
        if (!saveInstance.getString(Const.HIS_HER_NAME).equals(""))
            mEtHisHerName.setText(saveInstance.getString(Const.HIS_HER_NAME));
        if (!saveInstance.getString(Const.MY_NAME).equals(""))
            mEtMyName.setText(saveInstance.getString(Const.MY_NAME));
        if (!mRelationshipSince.equals("")) {
            mTvRelationshipDate.setText(mRelationshipSince);
        }
    }

}
