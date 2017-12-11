package com.heepie.soundhub.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heepie.soundhub.R;
import com.heepie.soundhub.domain.model.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunmin on 2017-12-11.
 */

public class UserPageMusicListAdapter extends RecyclerView.Adapter<UserPageMusicListAdapter.Holder>{
    List<String> data = new ArrayList<>();

    public UserPageMusicListAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String aaa = data.get(position);
        holder.textView.setText(aaa);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView textView;

        public Holder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.list_post_title);
        }
    }
}
