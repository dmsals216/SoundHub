package com.heepie.soundhub.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

/**
 * Created by Heepie on 2017. 12. 6..
 */

public class Comment_track implements Parcelable {
    private String id;

    // 코멘트 등록자
    private String author;

    // 해당 포스트 이름
    private String post;

    private String instrument;

    private String comment_track;

    @Expose
    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getAuthor ()
    {
        return author;
    }

    public void setAuthor (String author)
    {
        this.author = author;
    }

    public String getPost ()
    {
        return post;
    }

    public void setPost (String post)
    {
        this.post = post;
    }

    public String getInstrument ()
    {
        return instrument;
    }

    public void setInstrument (String instrument)
    {
        this.instrument = instrument;
    }

    public String getComment_track ()
    {
        return comment_track;
    }

    public void setComment_track (String comment_track)
    {
        this.comment_track = comment_track;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.author);
        dest.writeString(this.post);
        dest.writeString(this.instrument);
        dest.writeString(this.comment_track);
    }

    public Comment_track() {
        isCheck = false;
    }

    protected Comment_track(Parcel in) {
        this.id = in.readString();
        this.author = in.readString();
        this.post = in.readString();
        this.instrument = in.readString();
        this.comment_track = in.readString();
    }

    public static final Creator<Comment_track> CREATOR = new Creator<Comment_track>() {
        @Override
        public Comment_track createFromParcel(Parcel source) {
            return new Comment_track(source);
        }

        @Override
        public Comment_track[] newArray(int size) {
            return new Comment_track[size];
        }
    };
}