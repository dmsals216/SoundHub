package com.heepie.soundhub.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heepie.soundhub.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunmin on 2017-12-11.
 */

public class UserPageUserListAdapter extends RecyclerView.Adapter<UserPageUserListAdapter.Holder>{
    List<String> data = new ArrayList<>();

    public UserPageUserListAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
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
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
