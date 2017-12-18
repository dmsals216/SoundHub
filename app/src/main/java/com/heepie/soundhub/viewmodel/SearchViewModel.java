package com.heepie.soundhub.viewmodel;

import android.annotation.SuppressLint;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import com.heepie.soundhub.Interfaces.SearchDao;
import com.heepie.soundhub.adapter.SearchViewTitleAdapter;
import com.heepie.soundhub.databinding.SearchViewBinding;
import com.heepie.soundhub.domain.model.SearchModel;
import com.heepie.soundhub.utils.DBHelper;

import java.util.List;

/**
 * Created by eunmin on 2017-12-18.
 */

public class SearchViewModel {

    public ObservableField<String> searchtitle = new ObservableField<>("");
    public ObservableBoolean viewMode = new ObservableBoolean(false);

    public SearchViewTitleAdapter adapter;
    public SearchViewModel(SearchViewTitleAdapter adapter) {
        this.adapter = adapter;
    }

    public void onClickedText(View view) {
        DBHelper dbHelper =  DBHelper.getInstance(view.getContext());
        SearchDao dao = dbHelper.searchDao();
        List<SearchModel> data = dao.getAll();
        adapter.setData(data);
        viewMode.set(true);
    }

    public void onClickedSearch(View view) {
        if(!searchtitle.get().equals("")) {
            DBHelper dbHelper =  DBHelper.getInstance(view.getContext());
            SearchDao dao = dbHelper.searchDao();
            SearchModel model = new SearchModel(searchtitle.get());
            dao.insertItem(model);
            searchtitle.set("");
            viewMode.set(false);
        }else {
            Toast.makeText(view.getContext(), "검색어를 입력해 주세요.", Toast.LENGTH_SHORT).show();
        }
    }
}