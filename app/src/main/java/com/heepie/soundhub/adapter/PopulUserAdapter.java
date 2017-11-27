package com.heepie.soundhub.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.heepie.soundhub.R;
import com.heepie.soundhub.databinding.ItemUserBinding;
import com.heepie.soundhub.viewmodel.UserViewModel;

/**
 * Created by Heepie on 2017. 11. 27..
 */

public class PopulUserAdapter extends RecyclerView.Adapter<PopulUserAdapter.Holder> {
    ObservableArrayList<UserViewModel> mItems;

    public void setItems(ObservableArrayList<UserViewModel> mItems) {
        this.mItems = mItems;
    }

    @Override
    public PopulUserAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemUserBinding itemUserBinding = DataBindingUtil.inflate(inflater, R.layout.item_user, parent,false);
        PopulUserAdapter.Holder userHolder = new PopulUserAdapter.Holder(itemUserBinding);

        return userHolder;
    }

    @Override
    public void onBindViewHolder(PopulUserAdapter.Holder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        if (mItems == null)
            return 0;
        return mItems.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private ItemUserBinding mBinding;

        public Holder(ItemUserBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(@NonNull UserViewModel viewModel) {
            mBinding.setModel(viewModel.getModel());
            mBinding.executePendingBindings();
        }
    }
}