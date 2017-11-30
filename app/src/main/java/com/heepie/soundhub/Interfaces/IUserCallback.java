package com.heepie.soundhub.Interfaces;

import com.heepie.soundhub.domain.model.User;

import java.util.List;

/**
 * Created by Heepie on 2017. 11. 30..
 */

public interface IUserCallback {
    void initData(int code, String msg, List<User> data);
}
