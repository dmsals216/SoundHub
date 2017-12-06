package com.heepie.soundhub.domain.model;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Heepie on 2017. 11. 30..
 */

public class Post implements Parcelable {
    private String author_track;

    private String created_date;

    private String genre;

    private String id;

    private String num_comments;

    private String num_liked;

    private User author;

    private String title;

    private String master_track;

    private String[] liked;

    private HashMap<String, List<Comment_track>> comment_tracks;

    private String instrument;

    public Post() {

    }

    public String getAuthor_track() {
        return author_track;
    }

    public void setAuthor_track(String author_track) {
        this.author_track = author_track;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum_comments() {
        return num_comments;
    }

    public void setNum_comments(String num_comments) {
        this.num_comments = num_comments;
    }

    public String getNum_liked() {
        return num_liked;
    }

    public void setNum_liked(String num_liked) {
        this.num_liked = num_liked;
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

    public String[] getLiked() {
        return liked;
    }

    public void setLiked(String[] liked) {
        this.liked = liked;
    }

    public HashMap<String, List<Comment_track>> getComment_tracks() {
        return comment_tracks;
    }

    public void setComment_tracks(HashMap<String, List<Comment_track>> comment_tracks) {
        this.comment_tracks = comment_tracks;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    protected Post(Parcel in) {
        author_track = in.readString();
        created_date = in.readString();
        genre = in.readString();
        id = in.readString();
        num_comments = in.readString();
        num_liked = in.readString();
        author = in.readParcelable(User.class.getClassLoader());
        title = in.readString();
        master_track = in.readString();
        liked = in.createStringArray();
        instrument = in.readString();
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author_track);
        dest.writeString(created_date);
        dest.writeString(genre);
        dest.writeString(id);
        dest.writeString(num_comments);
        dest.writeString(num_liked);
        dest.writeParcelable(author, flags);
        dest.writeString(title);
        dest.writeString(master_track);
        dest.writeStringArray(liked);
        dest.writeString(instrument);
    }
}

