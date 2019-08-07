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

import com.example.lovedays.Adapter.AnniversaryRecyclerviewAdapter;
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

                Calendar todayCalendar = Calendar.getInstance();
                Date today = new Date();
                todayCalendar.setTime(today);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/M/dd");
                for (int i = 0; i < 36500; i++) {
                    startCalendar.setTime(dateRelationship);
                    startCalendar.add(Calendar.DATE, i);
                    Date date = dateFormat.parse(startCalendar.get(Calendar.YEAR) + "/"
                            + (startCalendar.get(Calendar.MONTH) + 1) + "/"
                            + startCalendar.get(Calendar.DATE));
                    long diff = (date.getTime() - today.getTime()) / (24 * 60 * 60 * 1000);
                    if (diff == 0.0)
                        continue;
                    else if ((int) diff % 365 == 0)
                        list.add(new Anniversary(today.after(date),
                                dateFormat.format(date.getTime()),
                                i + "주년",
                                (int) diff));
                    else if ((int) diff % 100 == 0)
                        list.add(new Anniversary(today.after(date),
                                dateFormat.format(date.getTime()),
                                (i * 100) + "일",
                                (int) diff));
                }
                return list;
            }

            @Override
            protected void onPostExecute(ArrayList result) {
                AnniversaryRecyclerviewAdapter adapter = new AnniversaryRecyclerviewAdapter(result);
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
