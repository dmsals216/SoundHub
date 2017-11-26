package com.heepie.soundhub.binding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.heepie.soundhub.adapter.RecyclerViewAdapter;
import com.heepie.soundhub.utils.IModelGettable;

/**
 * Created by Heepie on 2017. 11. 25..
 * RecyclerView에서 공통으로 사용하는
 */

public class RecyclerViewBinding {
    @BindingAdapter("items")
    public static <T extends IModelGettable> void setItems(View view, ObservableArrayList<T> items) {
        if (view instanceof RecyclerView) {
            RecyclerViewAdapter<T> adapter = (RecyclerViewAdapter<T>)((RecyclerView)view).getAdapter();
            adapter.setmItems(items);
        }
    }

    @BindingAdapter("adapterAndlayout")
    public static void setAdapterAndLayoutManager(View view, Context context) {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter<>();
        ((RecyclerView)view).setAdapter(adapter);
        ((RecyclerView)view).setLayoutManager(new LinearLayoutManager(context));

    }

    /* @BindingAdapter 인자 2개 넣기 예제
    <ImageView
            android:layout_width="@dimen/place_holder_size"
            android:layout_height="@dimen/place_holder_size"
            android:layout_alignParentRight="true"
            android:layout_alignParentTop="true"
            android:layout_centerVertical="true"
            app:url="@{image.imageUrl}"
            app:size="@{@dimen/place_holder_size}"
            />


    @BindingAdapter({"bind:url", "bind:size"})
    public static void loadImage(ImageView imageView, String url, int size) {
        if (!Strings.isNullOrEmpty(url)) {
            Picasso.with(imageView.getContext()).load(url).resize(size, size).centerCrop().into(imageView);
        }
    }*/
}
