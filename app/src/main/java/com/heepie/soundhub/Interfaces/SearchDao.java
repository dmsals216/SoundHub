package com.heepie.soundhub.Interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.heepie.soundhub.domain.model.SearchModel;

import java.util.List;

/**
 * Created by eunmin on 2017-12-18.
 */

@Dao
public interface SearchDao {
    @Query("select * from search order by id desc")
    List<SearchModel> getAll();

    @Insert
    void insertItem(SearchModel model);
}
