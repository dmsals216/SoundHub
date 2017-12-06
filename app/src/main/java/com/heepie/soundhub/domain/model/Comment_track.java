package com.heepie.soundhub.domain.model;

/**
 * Created by Heepie on 2017. 12. 6..
 */

public class Comment_track {
    private String id;

    // 코멘트 등록자
    private String author;

    // 해당 포스트 이름
    private String post;

    private String instrument;

    private String comment_track;

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
}