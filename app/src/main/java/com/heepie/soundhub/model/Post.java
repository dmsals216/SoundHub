package com.heepie.soundhub.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Heepie on 2017. 11. 24..
 * 바인딩을 할 때는 무조건 private가 아닌 public으로 해야한다.
 */

public class Post implements Parcelable {
    public User user;
    public String title;
    public int post_image_path;
    public String music_length;
    public String like_count;
    public String comment_count;
    public String tag;
    public boolean isShow;

    public Post() {

    }

    public Post(User user, String title, int post_image_path, String music_length, String like_count, String comment_count, String tag, boolean isShow) {
        this.user = user;
        this.title = title;
        this.post_image_path = post_image_path;
        this.music_length = music_length;
        this.like_count = like_count;
        this.comment_count = comment_count;
        this.tag = tag;
        this.isShow = isShow;
    }


    protected Post(Parcel in) {
        user = in.readParcelable(User.class.getClassLoader());
        title = in.readString();
        post_image_path = in.readInt();
        music_length = in.readString();
        like_count = in.readString();
        comment_count = in.readString();
        tag = in.readString();
        isShow = in.readByte() != 0;
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    @Override
    public String toString() {
        return user.user_name +" " + user.user_image_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(user, flags);
        dest.writeString(title);
        dest.writeInt(post_image_path);
        dest.writeString(music_length);
        dest.writeString(like_count);
        dest.writeString(comment_count);
        dest.writeString(tag);
        dest.writeByte((byte) (isShow ? 1 : 0));
    }
}
