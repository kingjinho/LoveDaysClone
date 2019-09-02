package com.example.lovedays.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lovedays.Model.Anniversary;
import com.example.lovedays.R;

import java.util.ArrayList;

/**
 * Created by KING JINHO on 2019-07-29
 */
class AnniversaryViewHolder extends RecyclerView.ViewHolder {

    ImageView ivCurrent;
    TextView tvAnniversary;
    TextView tvUpcoming;
    TextView tvDate;
    TextView tvDDay;

    public AnniversaryViewHolder(@NonNull View itemView) {
        super(itemView);
        ivCurrent = itemView.findViewById(R.id.iv_current);
        tvAnniversary = itemView.findViewById(R.id.tv_anniversary);
        tvDate = itemView.findViewById(R.id.tv_date);
        tvDDay = itemView.findViewById(R.id.tv_dDay);
        tvUpcoming = itemView.findViewById(R.id.tv_upcoming);
    }
}

class SettingViewHolder extends RecyclerView.ViewHolder {
    public SettingViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList data;
    private Context context;


    public RecyclerViewAdapter(ArrayList data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position) instanceof Anniversary)
            return 1;
        else
            return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.anniversary_cardview, parent, false);
            return new SettingViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.anniversary_cardview, parent, false);
            return new AnniversaryViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                break;
            case 1:
                AnniversaryViewHolder anniversaryViewHolder = (AnniversaryViewHolder) holder;
                Anniversary currentItem = (Anniversary) data.get(position);
                anniversaryViewHolder.tvAnniversary.setText(currentItem.getNameAnniversary());
                anniversaryViewHolder.tvDDay.setText(currentItem.getDateFromToday());
                anniversaryViewHolder.tvDate.setText(currentItem.getDateAnniversary());
                if (currentItem.isPassed()) {
                    anniversaryViewHolder.ivCurrent.setVisibility(View.INVISIBLE);
                    anniversaryViewHolder.tvUpcoming.setVisibility(View.INVISIBLE);
                    anniversaryViewHolder.tvAnniversary.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
                    anniversaryViewHolder.tvDDay.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
                    anniversaryViewHolder.tvDate.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
                } else {
                    anniversaryViewHolder.tvAnniversary.setTextColor(ContextCompat.getColor(context, R.color.white));
                    anniversaryViewHolder.tvDDay.setTextColor(ContextCompat.getColor(context, R.color.white));
                    anniversaryViewHolder.tvDate.setTextColor(ContextCompat.getColor(context, R.color.white));
                }
                if (!currentItem.isUpcoming()) {
                    anniversaryViewHolder.tvUpcoming.setVisibility(View.INVISIBLE);
                    anniversaryViewHolder.ivCurrent.setVisibility(View.INVISIBLE);
                } else {
                    anniversaryViewHolder.tvUpcoming.setVisibility(View.VISIBLE);
                    anniversaryViewHolder.ivCurrent.setVisibility(View.VISIBLE);
                }
                break;
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
