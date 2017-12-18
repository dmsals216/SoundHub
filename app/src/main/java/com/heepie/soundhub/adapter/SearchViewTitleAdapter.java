package com.heepie.soundhub.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heepie.soundhub.R;
import com.heepie.soundhub.domain.model.SearchModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunmin on 2017-12-18.
 */

public class SearchViewTitleAdapter extends RecyclerView.Adapter<SearchViewTitleAdapter.Holder>{
    List<SearchModel> data = new ArrayList<>();

    public void setData(List<SearchModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_title_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        SearchModel model = data.get(position);
        holder.textView.setText(model.getSearch());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView textView;
        public Holder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView26);
        }
    }
}
