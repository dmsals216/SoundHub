package com.heepie.soundhub.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.heepie.soundhub.domain.model.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunmin on 2017-12-22.
 */

public class SearchPostListAdapter extends RecyclerView.Adapter<SearchPostListAdapter.Holder>{

    List<Post> posts = new ArrayList<>();

    public SearchPostListAdapter(List<Post> posts) {
        this. posts = posts;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }
}
