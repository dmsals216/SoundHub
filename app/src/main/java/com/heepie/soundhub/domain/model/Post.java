package com.heepie.soundhub.domain.model;

/**
 * Created by Heepie on 2017. 11. 30..
 */

public class Post
{
    private String author_track;

    private String id;

    private User author;

    private String title;

    private String master_track;

    private Comment_tracks[] comment_tracks;

    public String getAuthor_track ()
    {
        return author_track;
    }

    public void setAuthor_track (String author_track)
    {
        this.author_track = author_track;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public User getAuthor ()
    {
        return author;
    }

    public void setAuthor (User author)
    {
        this.author = author;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getMaster_track ()
{
    return master_track;
}

    public void setMaster_track (String master_track)
    {
        this.master_track = master_track;
    }

    public Comment_tracks[] getComment_tracks ()
    {
        return comment_tracks;
    }

    public void setComment_tracks (Comment_tracks[] comment_tracks)
    {
        this.comment_tracks = comment_tracks;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [author_track = "+author_track+", id = "+id+", author = "+author+", title = "+title+", master_track = "+master_track+", comment_tracks = "+comment_tracks+"]";
    }
}

