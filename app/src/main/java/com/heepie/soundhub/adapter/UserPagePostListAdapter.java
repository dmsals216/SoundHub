package com.heepie.soundhub.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heepie.soundhub.R;
import com.heepie.soundhub.domain.model.Post;
import com.heepie.soundhub.domain.model.User_Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunmin on 2017-12-11.
 */

public class UserPagePostListAdapter extends RecyclerView.Adapter<UserPagePostListAdapter.Holder>{
    List<User_Post> data = new ArrayList<>();

    public UserPagePostListAdapter(List<User_Post> data) {
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userpage_post_list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        User_Post post = data.get(position);
        holder.textTitle.setText(post.getTitle() + "");
        holder.textHeart.setText(post.getNum_liked() + "");
        holder.textComments.setText(post.getNum_comments() + "");
        Log.e("haha", post.getId() + "");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView textTitle;
        TextView textArtist;
        TextView textHeart;
        TextView textComments;

        public Holder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.userpageposttitle);
            textArtist = itemView.findViewById(R.id.userpagepostartist);
            textHeart = itemView.findViewById(R.id.userpageheart);
            textComments = itemView.findViewById(R.id.userpagecomments);
        }
    }
}
