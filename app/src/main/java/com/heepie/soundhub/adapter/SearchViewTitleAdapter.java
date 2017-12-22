package com.heepie.soundhub.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.heepie.soundhub.Interfaces.SearchDao;
import com.heepie.soundhub.R;
import com.heepie.soundhub.domain.model.Search;
import com.heepie.soundhub.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunmin on 2017-12-18.
 */

public class SearchViewTitleAdapter extends RecyclerView.Adapter<SearchViewTitleAdapter.Holder>{
    List<Search> data = new ArrayList<>();

    public void setData(List<Search> data) {
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
        Search model = data.get(position);
        holder.textView.setText(model.getSearch());
        holder.imageView.setOnClickListener(view -> {
            DBHelper dbHelper =  DBHelper.getInstance(view.getContext());
            SearchDao dao = dbHelper.searchDao();
            dao.deleteItem(model);
            setData(dao.getAll());
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public Holder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView26);
            imageView = itemView.findViewById(R.id.imageView9);
        }
    }
}
