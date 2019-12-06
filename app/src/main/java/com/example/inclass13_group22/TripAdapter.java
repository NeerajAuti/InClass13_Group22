package com.example.inclass13_group22;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {
    List<Trip> mList;
    private final OnItemClickListener listener;

    public TripAdapter(List<Trip> mList, OnItemClickListener listener) {
        this.mList = mList;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Trip item);
    }

    @NonNull
    @Override
    public TripAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_trip, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TripAdapter.ViewHolder holder, int position) {
        holder.bind(mList.get(position), listener);
        Trip trip = mList.get(position);

        holder.tv_tripName.setText(trip.tripName);
        holder.tv_city.setText(trip.city);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_tripName, tv_city;
        FloatingActionButton fab_addPlace, fab_map;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tripName = itemView.findViewById(R.id.tv_tripName);
            tv_city = itemView.findViewById(R.id.tv_city);
            fab_addPlace = itemView.findViewById(R.id.fab_addPlace);
            fab_map = itemView.findViewById(R.id.fab_map);
        }

        public void bind(final Trip trip, OnItemClickListener listener) {

        }
    }
}
