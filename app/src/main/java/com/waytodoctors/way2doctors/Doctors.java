package com.waytodoctors.way2doctors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Doctors extends AppCompatActivity {

    private List<DoctorsList> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DoctorsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new DoctorsAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareDoctorData();
    }


    private void prepareDoctorData() {
        DoctorsList movie = new DoctorsList("Mad Max: Fury Road", "Action & Adventure");
        movieList.add(movie);

        movie = new DoctorsList("Inside Out", "Animation, Kids & Family");
        movieList.add(movie);

        movie = new DoctorsList("Star Wars: Episode VII - The Force Awakens","");
        movieList.add(movie);

        movie = new DoctorsList("Shaun the Sheep", "Animation");
        movieList.add(movie);

        movie = new DoctorsList("The Martian", "Science Fiction & Fantasy");
        movieList.add(movie);

        movie = new DoctorsList("Mission: Impossible Rogue Nation", "Action");
        movieList.add(movie);

        movie = new DoctorsList("Up", "Animation");
        movieList.add(movie);

        movie = new DoctorsList("Star Trek", "Science Fiction");
        movieList.add(movie);

        movie = new DoctorsList("The LEGO Movie", "Animation");
        movieList.add(movie);

        movie = new DoctorsList("Iron Man", "Action & Adventure");
        movieList.add(movie);

        movie = new DoctorsList("Aliens", "Science Fiction");
        movieList.add(movie);

        movie = new DoctorsList("Chicken Run", "Animation");
        movieList.add(movie);

        movie = new DoctorsList("Back to the Future", "Science Fiction");
        movieList.add(movie);

        movie = new DoctorsList("Raiders of the Lost Ark", "Action & Adventure");
        movieList.add(movie);

        movie = new DoctorsList("Goldfinger", "Action & Adventure");
        movieList.add(movie);

        movie = new DoctorsList("Guardians of the Galaxy", "Science Fiction & Fantasy");
        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
    }
}
