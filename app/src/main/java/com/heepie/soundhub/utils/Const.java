package com.heepie.soundhub.utils;

import com.heepie.soundhub.domain.model.User;

/**
 * Created by Heepie on 2017. 11. 25..
 */

public class Const {
    public static final int VIEW_TYPE_POPULAR_USER = 1;
    public static final int VIEW_TYPE_POPULAR_POST = 2;
    public static final int VIEW_TYPE_NEW_POST = 3;

    public static final int DEFAULT_COUNT_OF_SHOW_ITEM = 5;
    public static final int MAX_COUNT_OF_SHOW_ITEM = 15;

    public static final String ACTION_MUSIC_NOT_INIT = "NOT_INIT";
    public static final String ACTION_MUSIC_PLAY = "PLAY";
    public static final String ACTION_MUSIC_PAUSE = "PAUSE";

    public static final int SELECT_AUDIO_TRACK = 50;

    public static final int RESULT_SUCCESS = 200;


    public static User user;
    public static String TOKEN;

    public static final String CATEGORY_DEFAULT = "CATEGORY_DEFAULT";

}
