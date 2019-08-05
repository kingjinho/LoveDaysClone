package com.example.lovedays.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lovedays.R;

/**
 * Created by KING JINHO on 2019-07-29
 */
public class AnniversaryRecyclerviewAdapter extends RecyclerView.Adapter<AnniversaryRecyclerviewAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivCurrent;
        TextView tvAnniversary;
        TextView tvUpcoming;
        TextView tvDate;
        TextView tvDDay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCurrent = itemView.findViewById(R.id.iv_current);
            tvAnniversary = itemView.findViewById(R.id.tv_anniversary);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvDDay = itemView.findViewById(R.id.tv_dDay);
            tvUpcoming = itemView.findViewById(R.id.tv_upcoming);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.anniversary_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
