package com.example.lovedays.Screens;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lovedays.Adapter.RecyclerViewAdapter;
import com.example.lovedays.Model.Anniversary;
import com.example.lovedays.R;
import com.example.lovedays.Utils.AnniversaryAsyncTask;
import com.example.lovedays.Utils.Const;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by KING JINHO on 2019-07-29
 */
public class MainScreenAnniversaryList extends AbsFragment {
    public static final String TAG = MainScreenAnniversaryList.class.getSimpleName();
    private RecyclerView mRecyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setAnniversaryRecyclerView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainscreen_anniversary, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_anniversary);
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


    private void setAnniversaryRecyclerView() {
        String dateRelationshipStart = root.getSharedPreferences(Const.USER, Context.MODE_PRIVATE).getString(Const.RELATIONSHIP_START, "");
        ArrayList<Anniversary> list = new ArrayList<>();

        AnniversaryAsyncTask.execute(root, new AnniversaryAsyncTask.onExecuteCallback<ArrayList>() {
            @Override
            public ArrayList doInBackground() throws Exception {
                Date dateRelationship = new SimpleDateFormat("yyyy/M/dd").parse(dateRelationshipStart);
                Calendar startCalendar = Calendar.getInstance();
                startCalendar.setTime(dateRelationship);

                Calendar calendarComparison = Calendar.getInstance();
                calendarComparison.setTime(dateRelationship);

                Calendar todayCalendar = Calendar.getInstance();
                Date today = new Date();
                todayCalendar.setTime(today);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/M/dd");
                for (int i = 1; i <= 3650; i++) {
                    calendarComparison.setTime(dateRelationship);
                    calendarComparison.add(Calendar.DATE, i);

                    //더한 값
                    Date date = dateFormat.parse(calendarComparison.get(Calendar.YEAR) + "/"
                            + (calendarComparison.get(Calendar.MONTH) + 1) + "/"
                            + calendarComparison.get(Calendar.DATE));

                    int dayMilliSeconds = 24 * 60 * 60 * 1000;
                    int dateCountFromRelationship = (int) ((date.getTime() - dateRelationship.getTime()) / dayMilliSeconds);
                    int dateCountFromToday = (int) ((date.getTime() - today.getTime()) / dayMilliSeconds) + 1;

                    if (dateCountFromRelationship == 0.0)
                        continue;
                    else if (calendarComparison.get(Calendar.MONTH) == startCalendar.get(Calendar.MONTH) && calendarComparison.get(Calendar.DATE) == startCalendar.get(Calendar.DATE))
                        list.add(new Anniversary(today.after(date),
                                dateCountFromToday > 0 && dateCountFromToday <= 100,
                                dateFormat.format(date.getTime()),
                                calendarComparison.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR) + "주년", dateCountFromToday + 1));
                    /*else if (dateCountFromRelationship > 365 && dateCountFromRelationship % 365 == 1)
                        list.add(new Anniversary(today.after(date), dateFormat.format(date.getTime()),
                                dateCountFromRelationship / 365 + "주년", dateCountFromToday));*/
                    else if (dateCountFromRelationship % 100 == 0)
                        list.add(new Anniversary(today.after(date),
                                dateCountFromToday > 0 && dateCountFromToday <= 100,
                                dateFormat.format(date.getTime() - dayMilliSeconds),
                                dateCountFromRelationship + "일", dateCountFromToday));
                }
                return list;
            }

            @Override
            protected void onPostExecute(ArrayList result) {
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(result, root);
                LinearLayoutManager manager = new LinearLayoutManager(root);
                manager.setOrientation(RecyclerView.VERTICAL);
                manager.setItemPrefetchEnabled(true);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(manager);
                mRecyclerView.setAdapter(adapter);
            }
        });
    }
}
