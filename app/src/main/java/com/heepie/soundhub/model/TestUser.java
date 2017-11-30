package com.heepie.soundhub.model;

/**
 * Created by Heepie on 2017. 11. 24..
 */

public class TestUser {
    public String user_name;
    public int user_image_path;
    public String user_like_count;


    public TestUser(String user_name, int user_image_path, String user_like_count) {
        this.user_name = user_name;
        this.user_image_path = user_image_path;
        this.user_like_count = user_like_count;
    }
}
