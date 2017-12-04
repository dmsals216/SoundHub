package com.heepie.soundhub.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Heepie on 2017. 11. 30..
 */

public class Comment_tracks implements Parcelable
{
    private String id;

    private String author;

    private String post;

    private String instrument;

    private String comment_track;

    protected Comment_tracks(Parcel in) {
        id = in.readString();
        author = in.readString();
        post = in.readString();
        instrument = in.readString();
        comment_track = in.readString();
    }

    public static final Creator<Comment_tracks> CREATOR = new Creator<Comment_tracks>() {
        @Override
        public Comment_tracks createFromParcel(Parcel in) {
            return new Comment_tracks(in);
        }

        @Override
        public Comment_tracks[] newArray(int size) {
            return new Comment_tracks[size];
        }
    };

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
    public String toString()
    {
        return "ClassPojo [id = "+id+", author = "+author+", post = "+post+", instrument = "+instrument+", comment_track = "+comment_track+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(author);
        dest.writeString(post);
        dest.writeString(instrument);
        dest.writeString(comment_track);
    }
}