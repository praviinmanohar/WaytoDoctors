package com.waytodoctors.way2doctors;

import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class DoctorsAdapter extends RecyclerView.Adapter<DoctorsAdapter.MyViewHolder> {

    private List<DoctorsList> doctorsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.name);
            genre = (TextView) view.findViewById(R.id.mobile);

        }
    }


    public DoctorsAdapter(List<DoctorsList> doctorsList) {
        this.doctorsList = doctorsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctors_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DoctorsList movie = doctorsList.get(position);
        holder.title.setText(movie.getName());
        holder.genre.setText(movie.getMobile());

    }

    @Override
    public int getItemCount() {
        return doctorsList.size();
    }
}