package com.heepie.soundhub.domain.model;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by Heepie on 2017. 11. 30..
 */

public class Post implements Parcelable
{
    private String author_track;

    private String id;

    private User author;

    private String title;

    private String master_track;

    private List<Comment_tracks> comment_tracks;

    public String getAuthor_track() {
        return author_track;
    }

    public void setAuthor_track(String author_track) {
        this.author_track = author_track;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaster_track() {
        return master_track;
    }

    public void setMaster_track(String master_track) {
        this.master_track = master_track;
    }

    public List<Comment_tracks> getComment_tracks() {
        return comment_tracks;
    }

    public void setComment_tracks(List<Comment_tracks> comment_tracks) {
        this.comment_tracks = comment_tracks;
    }

    public Post(String author_track, User author, String title, String master_track) {
        this.author_track = author_track;
        this.author = author;
        this.title = title;
        this.master_track = master_track;
    }

    @Expose()
    public boolean isShow = true;

    protected Post(Parcel in) {
        author_track = in.readString();
        id = in.readString();
        author = in.readParcelable(User.class.getClassLoader());
        title = in.readString();
        master_track = in.readString();
        comment_tracks = in.createTypedArrayList(Comment_tracks.CREATOR);
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
    public String toString()
    {
        return "ClassPojo [author_track = "+author_track+", id = "+id+", author = "+author+", title = "+title+", master_track = "+master_track+", comment_tracks = "+comment_tracks+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author_track);
        dest.writeString(id);
        dest.writeParcelable(author, flags);
        dest.writeString(title);
        dest.writeString(master_track);
        dest.writeTypedList(comment_tracks);
        dest.writeByte((byte) (isShow ? 1 : 0));
    }
}

