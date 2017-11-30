package com.heepie.soundhub.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.heepie.soundhub.BR;
import com.heepie.soundhub.Interfaces.IModelGettable;
import com.heepie.soundhub.Interfaces.IShowable;

/**
 * Created by Heepie on 2017. 11. 28..
 */

public class InnerRecyclerAdapter<T extends IModelGettable, F extends IShowable> extends RecyclerView.Adapter<InnerRecyclerAdapter.Holder> {
    private int index;
    ObservableArrayList<T> mItems;
    ObservableArrayList<T> realItems;
    int layoutResId;

    public InnerRecyclerAdapter() {
//        index=0;
    }

    public void setItems(ObservableArrayList<T> mItems, int layoutResId) {
        this.mItems = mItems;
        this.layoutResId = layoutResId;

        // ture 데이터 개수 확인
        /*for (int i=0; i<mItems.size(); i=i+1) {
            F model = (F)mItems.get(i);
            if (model.isShow()) {
                index += 1;
            }
        }*/
    }

    @Override
    public InnerRecyclerAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutResId, parent,false);
        InnerRecyclerAdapter.Holder holder = new InnerRecyclerAdapter.Holder(binding);

        return holder;
    }

    @Override
    public void onBindViewHolder(InnerRecyclerAdapter.Holder holder, int position) {
        F model = (F)mItems.get(position);

        Log.e("heepie", layoutResId + "    " + model.isShow() + "");

        if (model.isShow())
            holder.bind(mItems.get(position).getModel());
    }

    @Override
    public int getItemCount() {
        if (mItems == null)
            return 0;

        return mItems.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ViewDataBinding mBinding;

        public Holder(ViewDataBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(@NonNull Object obj) {
            mBinding.setVariable(BR.model, obj);
            mBinding.executePendingBindings();
        }
    }
}
