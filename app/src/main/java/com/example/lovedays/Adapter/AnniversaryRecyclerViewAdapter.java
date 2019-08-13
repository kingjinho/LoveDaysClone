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
public class AnniversaryRecyclerViewAdapter extends RecyclerView.Adapter<AnniversaryRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Anniversary> data;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

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

    public AnniversaryRecyclerViewAdapter(ArrayList data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.anniversary_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Anniversary currentItem = data.get(position);
        holder.tvAnniversary.setText(currentItem.getNameAnniversary());
        holder.tvDDay.setText(currentItem.getDateFromToday());
        holder.tvDate.setText(currentItem.getDateAnniversary());
        if (currentItem.isPassed()) {
            holder.ivCurrent.setVisibility(View.INVISIBLE);
            holder.tvUpcoming.setVisibility(View.INVISIBLE);
            holder.tvAnniversary.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            holder.tvDDay.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            holder.tvDate.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        }
        if (!currentItem.isUpcoming()) {
            holder.tvUpcoming.setVisibility(View.INVISIBLE);
            holder.ivCurrent.setVisibility(View.INVISIBLE);
        } else {
            holder.tvUpcoming.setVisibility(View.VISIBLE);
            holder.ivCurrent.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
