package com.heepie.soundhub.binding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.heepie.soundhub.R;
import com.heepie.soundhub.adapter.InnerRecyclerAdapter;
import com.heepie.soundhub.adapter.OuterRecyViewAdapter;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.utils.IModelGettable;
import com.heepie.soundhub.viewmodel.ListViewModel;

/**
 * Created by Heepie on 2017. 11. 25..
 */

public class RecyclerViewBinding {
    @BindingAdapter({"setOuterViewModel", "context"})
    public static <T extends IModelGettable> void setViewModel(View view, ListViewModel items, Context context) {
        if (view instanceof RecyclerView) {

            OuterRecyViewAdapter<T> adapter = new OuterRecyViewAdapter<>();
            ((RecyclerView)view).setAdapter(adapter);
            ((RecyclerView)view).setLayoutManager(new LinearLayoutManager(context));

            adapter.setItems(items);
        }
    }

    @BindingAdapter({"setInnerViewModel", "layoutType", "context"})
    public static <T extends IModelGettable> void setInnerViewModel(View view, ObservableArrayList<T> items, int layoutType, Context context) {
        if (view instanceof RecyclerView) {
            int layoutResId=-1;

            // 어뎁터 생성 및 설정
            InnerRecyclerAdapter<T> adapter = new InnerRecyclerAdapter<>();
            ((RecyclerView)view).setAdapter(adapter);

            Log.i("heepie", "In Bind + " + adapter + " " + layoutType);

            // 레이아웃 리소트 설정 및 레이아웃 매니저 설정
            switch (layoutType) {
                case Const.VIEW_TYPE_POPULAR_USER:
                    layoutResId = R.layout.item_user;
                    ((RecyclerView)view).setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                    break;
                case Const.VIEW_TYPE_POPULAR_POST:
                    layoutResId = R.layout.item_post;
                    ((RecyclerView)view).setLayoutManager(new LinearLayoutManager(context));
                    break;
                case Const.VIEW_TYPE_NEW_POST:
                    layoutResId = R.layout.item_post;
                    ((RecyclerView)view).setLayoutManager(new LinearLayoutManager(context));
                    Log.i("heepie", "In Switch");
                    break;
            }

            // ViewModel 설정
            adapter.setItems(items, layoutResId);
        }
    }
}
