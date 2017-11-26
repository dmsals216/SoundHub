package com.heepie.soundhub.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heepie.soundhub.R;
import com.heepie.soundhub.databinding.ItemUserBinding;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.utils.IModelGettable;

/**
 * Created by Heepie on 2017. 11. 26..
 */

public class ViewPagerAdapter<T extends IModelGettable> extends PagerAdapter {
    ObservableArrayList<T> mItems = new ObservableArrayList<>();

    public void addItem(T item) {
        mItems.add(item);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

//        ItemUserBinding itemUserBinding = DataBindingUtil.inflate(inflater, R.layout.item_user, container,false);


        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        if (mItems == null)
            return 0;
        return mItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
