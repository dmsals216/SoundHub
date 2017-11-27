package com.heepie.soundhub.binding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.heepie.soundhub.adapter.ListRecyViewAdapter;
import com.heepie.soundhub.adapter.PopulPostAdapter;
import com.heepie.soundhub.adapter.PopulUserAdapter;
import com.heepie.soundhub.utils.IModelGettable;
import com.heepie.soundhub.viewmodel.ListViewModel;
import com.heepie.soundhub.viewmodel.PostViewModel;
import com.heepie.soundhub.viewmodel.UserViewModel;

/**
 * Created by Heepie on 2017. 11. 25..
 */

public class RecyclerViewBinding {


    @BindingAdapter("setViewModel")
    public static <T extends IModelGettable> void setViewModel(View view, ListViewModel items) {
        if (view instanceof RecyclerView) {
            ListRecyViewAdapter<T> adapter = (ListRecyViewAdapter<T>)((RecyclerView)view).getAdapter();
            adapter.setItems(items);
        }
    }

    @BindingAdapter("adapterAndlayout")
    public static void setAdapterAndLayoutManager(View view, Context context) {
        ListRecyViewAdapter adapter = new ListRecyViewAdapter<>();
        ((RecyclerView)view).setAdapter(adapter);
        ((RecyclerView)view).setLayoutManager(new LinearLayoutManager(context));

    }

    @BindingAdapter("setViewModelPopulUser")
    public static <T extends IModelGettable> void setViewModelPopulUser(View view, ObservableArrayList<UserViewModel> items) {
        if (view instanceof RecyclerView) {
            PopulUserAdapter adapter = (PopulUserAdapter)((RecyclerView)view).getAdapter();
            adapter.setItems(items);
        }
    }

    @BindingAdapter("adapterAndlayoutPopulUser")
    public static void setAdapterAndLayoutManagerPopulUser(View view, Context context) {
        PopulUserAdapter adapter = new PopulUserAdapter();
        ((RecyclerView)view).setAdapter(adapter);
        ((RecyclerView)view).setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
    }

    @BindingAdapter("setViewModelPopulPost")
    public static <T extends IModelGettable> void setViewModelPopulPost(View view, ObservableArrayList<PostViewModel> items) {
        if (view instanceof RecyclerView) {
            PopulPostAdapter adapter = (PopulPostAdapter)((RecyclerView)view).getAdapter();
            adapter.setItems(items);
        }
    }

    @BindingAdapter("adapterAndlayoutPopulPost")
    public static void setAdapterAndLayoutManagerPopulPost(View view, Context context) {
        PopulPostAdapter adapter = new PopulPostAdapter();
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
