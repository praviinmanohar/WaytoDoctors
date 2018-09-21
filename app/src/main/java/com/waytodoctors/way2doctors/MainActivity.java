package com.waytodoctors.way2doctors;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<HomeList> homeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private HomesAdapter mAdapter;
    private SliderLayout sliderLayout;
    private Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        sliderLayout = (SliderLayout) findViewById(R.id.imageSlider);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.removeShiftMode(navigation);

        typeface = Constants.getTxtType(this);

        mAdapter = new HomesAdapter(homeList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        prepareHomeData();
        setSliderViews();
    }

    private class HomesAdapter extends RecyclerView.Adapter<HomesAdapter.MyViewHolder> {
        private List<HomeList> hmList;
        private Context ctx;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title;
            public ImageView imageView;

            public MyViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.hometxt);
                imageView = (ImageView) view.findViewById(R.id.imageView);
                title.setTypeface(typeface);
            }
        }


        public HomesAdapter(List<HomeList> homeList,Context context) {
            this.hmList = homeList;
            this.ctx = context;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.home_list, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            HomeList movie = hmList.get(position);
            holder.title.setText(movie.getName());
            Glide.with(ctx).load(movie.getImage()).into(holder.imageView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(getApplicationContext(), Doctors.class);
                    startActivity(myIntent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return hmList.size();
        }
    }

    private void setSliderViews() {

        for (int i = 0; i <= 3; i++) {

            SliderView sliderView = new SliderView(getApplicationContext());

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

            sliderView.setImageScaleType(ImageView.ScaleType.MATRIX);
            //sliderView.setDescription("setDescription " + (i + 1));

            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(getApplicationContext(), "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }
    }

    private void prepareHomeData() {

        int[] covers = new int[]{

                R.drawable.home3x,

                R.drawable.shapex,

                R.drawable.ticket3x,

                R.drawable.ticket3x,
        };

        HomeList a = new HomeList("Allopathy", covers[0]);
        homeList.add(a);

        a = new HomeList("Siddha", covers[1]);
        homeList.add(a);

        a = new HomeList("Acupunture", covers[2]);
        homeList.add(a);

        a = new HomeList("Homeopathy", covers[3]);
        homeList.add(a);

        a = new HomeList("Veterinary", covers[3]);
        homeList.add(a);

        a = new HomeList("Fitness", covers[3]);
        homeList.add(a);

        a = new HomeList("Hospitals", covers[3]);
        homeList.add(a);

        a = new HomeList("Scan / Labs Center", covers[3]);
        homeList.add(a);

        a = new HomeList("Medical Shops", covers[3]);
        homeList.add(a);

        a = new HomeList("Medical Devices", covers[3]);
        homeList.add(a);

        mAdapter.notifyDataSetChanged();
    }


    private static class MyDividerItemDecoration extends RecyclerView.ItemDecoration {

        private static final int[] ATTRS = new int[]{
                android.R.attr.listDivider
        };

        public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

        public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

        private Drawable mDivider;
        private int mOrientation;
        private Context context;
        private int margin;

        public MyDividerItemDecoration(Context context, int orientation, int margin) {
            this.context = context;
            this.margin = margin;
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
            setOrientation(orientation);
        }

        public void setOrientation(int orientation) {
            if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
                throw new IllegalArgumentException("invalid orientation");
            }
            mOrientation = orientation;
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            if (mOrientation == VERTICAL_LIST) {
                drawVertical(c, parent);
            } else {
                drawHorizontal(c, parent);
            }
        }

        public void drawVertical(Canvas c, RecyclerView parent) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left + dpToPx(margin), top, right - dpToPx(margin), bottom);
                mDivider.draw(c);
            }
        }

        public void drawHorizontal(Canvas c, RecyclerView parent) {
            final int top = parent.getPaddingTop();
            final int bottom = parent.getHeight() - parent.getPaddingBottom();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int left = child.getRight() + params.rightMargin;
                final int right = left + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top + dpToPx(margin), right, bottom - dpToPx(margin));
                mDivider.draw(c);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if (mOrientation == VERTICAL_LIST) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            }
        }

        private int dpToPx(int dp) {
            Resources r = context.getResources();
            return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
        }
    }


    static class BottomNavigationViewHelper {

        @SuppressLint("RestrictedApi")
        static void removeShiftMode(BottomNavigationView view) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            try {
                Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
                shiftingMode.setAccessible(true);
                shiftingMode.setBoolean(menuView, false);
                shiftingMode.setAccessible(false);
                for (int i = 0; i < menuView.getChildCount(); i++) {
                    BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                    item.setShiftingMode(false);
                    // set once again checked value, so view will be updated
                    item.setChecked(item.getItemData().isChecked());
                }
            } catch (NoSuchFieldException e) {
                Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field");
            } catch (IllegalAccessException e) {
                Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode");
            }
        }
    }
}
