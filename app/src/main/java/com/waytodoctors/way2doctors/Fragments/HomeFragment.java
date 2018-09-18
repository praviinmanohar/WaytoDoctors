package com.waytodoctors.way2doctors.Fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;
import com.waytodoctors.way2doctors.Constants;
import com.waytodoctors.way2doctors.HomeItem;
import com.waytodoctors.way2doctors.MainActivity;
import com.waytodoctors.way2doctors.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private View rootView;
    private SliderLayout sliderLayout;
    private Typeface type;
    private RecyclerView recyclerGrid;
    private GridViewAdapter adapter;
    private List<HomeItem> menuList;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = Constants.getTxtType(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        sliderLayout = rootView.findViewById(R.id.imageSlider);
        recyclerGrid = rootView.findViewById(R.id.recyclerview);
        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.THIN_WORM); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(1); //set scroll delay in seconds :
        setSliderViews();
        setGridViews();
        return rootView;

    }

    private void setGridViews() {
        menuList = new ArrayList<>();
        adapter = new GridViewAdapter(getContext(), menuList);

        recyclerGrid.setLayoutManager(new GridLayoutManager(getContext(),2));

        recyclerGrid.setItemAnimator(new DefaultItemAnimator());

        recyclerGrid.setAdapter(adapter);
        prepareDatas();
    }

    private void prepareDatas() {
        int[] covers = new int[]{

                R.drawable.stethoscope,

                R.drawable.phone_call,

                R.drawable.ambulance_,

                R.drawable.hospital_,
        };

        HomeItem a = new HomeItem("Doctor",  covers[0]);
        menuList.add(a);

        a = new HomeItem("Toll Free", covers[2]);
        menuList.add(a);

        a = new HomeItem("Emergency",  covers[4]);
        menuList.add(a);

        a = new HomeItem("Hospital",  covers[5]);
        menuList.add(a);

        adapter.notifyDataSetChanged();
    }

    private void setSliderViews() {

        for (int i = 0; i <= 3; i++) {

            SliderView sliderView = new SliderView(getActivity());

            switch (i) {
                case 0:
                    sliderView.setImageUrl("https://images.pexels.com/photos/547114/pexels-photo-547114.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
                    break;
                case 1:
                    sliderView.setImageUrl("https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
                    break;
                case 2:
                    sliderView.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
                    break;
                case 3:
                    sliderView.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderView.setDescription("setDescription " + (i + 1));

            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(getActivity(), "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }
    }

    public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.MyViewHolder> {

        private List<HomeItem> gridList;
        private Context ctx;

        public GridViewAdapter(Context ctx, List<HomeItem> gridList) {
            this.gridList = gridList;
            this.ctx = ctx;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView txtView;
            ImageView imageview;
            CardView card_view;

            public MyViewHolder(View view) {
                super(view);
                txtView = (TextView) view.findViewById(R.id.grid_names);
                imageview = (ImageView) view.findViewById(R.id.imageview);
                card_view = (CardView) view.findViewById(R.id.card_view);

                txtView.setTypeface(type);
            }
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.home_grid, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            HomeItem album = gridList.get(position);

            holder.txtView.setText(album.getName());

            holder.card_view.setCardBackgroundColor(getResources().getColor(R.color.white));
            Glide.with(ctx).load(album.getImage()).into(holder.imageview);
            holder.txtView.setTextColor(getResources().getColor(R.color.black));
            holder.imageview.setColorFilter(getResources().getColor(R.color.Decline));

            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(ctx, "Hello", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return gridList.size();
        }
    }

}
