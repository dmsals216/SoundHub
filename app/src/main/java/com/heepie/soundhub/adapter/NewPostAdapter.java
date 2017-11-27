package com.heepie.soundhub.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heepie.soundhub.R;
import com.heepie.soundhub.databinding.ItemPostBinding;
import com.heepie.soundhub.viewmodel.PostViewModel;

/**
 * Created by Heepie on 2017. 11. 27..
 */

public class NewPostAdapter extends RecyclerView.Adapter<NewPostAdapter.Holder> {
    ObservableArrayList<PostViewModel> mItems;

    public void setItems(ObservableArrayList<PostViewModel> mItems) {
        this.mItems = mItems;
    }

    @Override
    public NewPostAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemPostBinding itemPostBinding = DataBindingUtil.inflate(inflater, R.layout.item_post, parent,false);
        NewPostAdapter.Holder userHolder = new NewPostAdapter.Holder(itemPostBinding);

        return userHolder;
    }

    @Override
    public void onBindViewHolder(NewPostAdapter.Holder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        if (mItems == null)
            return 0;
        return mItems.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private ItemPostBinding mBinding;

        public Holder(ItemPostBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(@NonNull PostViewModel viewModel) {
            mBinding.setModel(viewModel.getModel());
            mBinding.executePendingBindings();
        }
    }
}