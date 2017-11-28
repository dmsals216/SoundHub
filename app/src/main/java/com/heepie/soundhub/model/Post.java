package com.heepie.soundhub.model;

/**
 * Created by Heepie on 2017. 11. 24..
 * 바인딩을 할 때는 무조건 private가 아닌 public으로 해야한다.
 */

public class Post {
    public User user;
    public String title;
    public int post_image_path;
    public String music_length;
    public String like_count;
    public String comment_count;
    public String tag;
    public boolean isShow;

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


}
