package com.example.lovedays.Screens;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lovedays.Adapter.AnniversaryRecyclerviewAdapter;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainscreen_anniversary, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_anniversary);
        ArrayList<Anniversary> anniversaryList = getAnniversaries();
        AnniversaryRecyclerviewAdapter adapter = new AnniversaryRecyclerviewAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(root);
        manager.setItemPrefetchEnabled(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
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


    private ArrayList getAnniversaries() {
        String dateRelationshipStart = root.getSharedPreferences(Const.USER, Context.MODE_PRIVATE).getString(Const.RELATIONSHIP_START, "");
        ArrayList<Anniversary> list = new ArrayList<>();

        AnniversaryAsyncTask.execute(root, new AnniversaryAsyncTask.onExecuteCallback() {
            @Override
            public Boolean doInBackground() throws Exception {
                Date dateParsed = new SimpleDateFormat("YYYY/M/DD").parse(dateRelationshipStart);
                Calendar startCalendar = Calendar.getInstance();
                startCalendar.setTime(dateParsed);

                Calendar todayCalendar = Calendar.getInstance();
                Date today = new Date();
                todayCalendar.setTime(today);

                for (int i = 0; i < 100; i++) {
                    startCalendar.add(Calendar.DATE, i*100);
                    long diff = (dateParsed.getTime() - today.getTime()) / (24 * 60 * 60 * 1000);
                    list.add(today.after(dateParsed), (int)diff);
                    break;
                }

                return list.size() > 0;
            }
        });
        return list;
    }

    private class Anniversary {

        /*
        * 100일, 200일 300일 1주년 -> 시작날짜에서 100씩
        * 밑에 날짜: 시작날짜에서 차근차근
        * 지나간 날짜, 아직 안지난 날짜 색깔 구분: 오늘 날짜랑 비교해서 색깔 구분
        * 곧 다가올 날짜: 로직 추가 생각(아마 recyclerview에서 바로 전 아이템이랑 다음 아이템 비교해서 isPassed가 true,false각각 나오면 upcoming true
        *  D+100, D-100 : 오늘 날짜 - 기념일
        * */

        boolean isPassed;
        boolean isUpcoming;
        String dateAnniversary;     //기념일
        String dateFromToday = "D"; //오늘 날짜 - 각 기념일
        int dateFrom;

        public Anniversary(boolean isPassed, String dateAnniversary, int dateFrom) {
            this.isPassed = isPassed;
            this.isUpcoming = isUpcoming;
            this.dateAnniversary = dateAnniversary;
            dateFromToday += (this.isPassed? "+" +dateFrom : "-"+dateFrom);
        }
    }
}
