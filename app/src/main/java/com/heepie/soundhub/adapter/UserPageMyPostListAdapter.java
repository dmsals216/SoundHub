package com.heepie.soundhub.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.heepie.soundhub.BuildConfig;
import com.heepie.soundhub.R;
import com.heepie.soundhub.domain.model.User_Post;
import com.heepie.soundhub.utils.Const;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by eunmin on 2017-12-22.
 */

public class UserPageMyPostListAdapter extends RecyclerView.Adapter<UserPageMyPostListAdapter.Holder> {

    List<User_Post> data = new ArrayList<>();

    public UserPageMyPostListAdapter(List<User_Post> data) {
        this.data = data;
    }

    @Override
    public UserPageMyPostListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userpage_post_list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(UserPageMyPostListAdapter.Holder holder, int position) {
        User_Post post = data.get(position);
        holder.textTitle.setText(post.getTitle() + "");
        holder.textHeart.setText(post.getNum_liked() + "");
        holder.textComments.setText(post.getNum_comments() + "");
        holder.textArtist.setText(Const.user.getNickname());
        RequestOptions options1 = new RequestOptions().centerCrop().placeholder(R.drawable.user).error(R.drawable.user).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(holder.itemView.getContext()).load(BuildConfig.MEDIA_URL + Const.user.getProfile_img()).apply(options1).into(holder.userpagepostuimage);
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
        CircleImageView userpagepostuimage;

        public Holder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.userpageposttitle);
            textArtist = itemView.findViewById(R.id.userpagepostartist);
            textHeart = itemView.findViewById(R.id.userpageheart);
            textComments = itemView.findViewById(R.id.userpagecomments);
            userpagepostuimage = itemView.findViewById(R.id.userpagepostuimage);
        }
    }
}
