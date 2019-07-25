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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.lovedays.MainActivity;
import com.example.lovedays.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by KING JINHO on 2019-07-24
 */
public class InitialSetupScreen extends AbsFragment {

    private Button mBtnClear;
    private Button mBtnStart;
    private TextView mTvRelationshipDate;
    private EditText mEtMyName;
    private EditText mEtHisHerName;
    private EditText mEtMobileNumber;

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
        ConstraintLayout layout = mDefaultView.findViewById(R.id.fragment_container);
        View view = inflater.inflate(R.layout.initialsetupscreen, container, false);
        final Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DATE);
        mBtnStart = view.findViewById(R.id.btnStart);
        mBtnClear = view.findViewById(R.id.btnClear);
        mTvRelationshipDate = view.findViewById(R.id.tvDateSelect);
        mTvRelationshipDate.setText(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
        mTvRelationshipDate.setOnClickListener(v-> {
            DatePickerDialog dialog = new DatePickerDialog(root, R.style.DialogTheme, (datePicker, yearSelected, monthSelected, dateSelected) ->{
                Toast.makeText(root, "its all good", Toast.LENGTH_SHORT).show();
            }, year, month, dayOfMonth);

            dialog.show();
        });
        layout.addView(view);
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
