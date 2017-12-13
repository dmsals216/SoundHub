package com.heepie.soundhub.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heepie.soundhub.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunmin on 2017-12-11.
 */

public class UserPageTabAdatper extends PagerAdapter{

    @Override
    public int getCount() {
        return 4;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.userpage_recycler, null);
        RecyclerView recyclerView = view.findViewById(R.id.userpagerecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        List<String> data = new ArrayList<>();
        for(int i = 0; i < 1000; i++) {
            data.add(i+" 번째 데이터");
        }
        if(position == 0 || position == 1) {
            UserPagePostListAdapter adapter = new UserPagePostListAdapter(data);
            recyclerView.setAdapter(adapter);
        }else {
            UserPageUserListAdapter adapter = new UserPageUserListAdapter(data);
            recyclerView.setAdapter(adapter);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
