package com.heepie.soundhub.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heepie.soundhub.R;
import com.heepie.soundhub.databinding.ItemPostBinding;
import com.heepie.soundhub.databinding.ItemUserBinding;
import com.heepie.soundhub.model.Post;
import com.heepie.soundhub.model.User;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.utils.IModelGettable;
import com.heepie.soundhub.viewmodel.ListViewModel;
import com.heepie.soundhub.viewmodel.PostViewModel;
import com.heepie.soundhub.viewmodel.UserViewModel;

/**
 * Created by Heepie on 2017. 11. 27..
 */

public class ListRecyViewAdapter<T extends IModelGettable> extends RecyclerView.Adapter<ListRecyViewAdapter.ListHolder> {
    ListViewModel mItems;
    UserViewModel uVmodel;
    PostViewModel pVModel;

    public void setItems(ListViewModel mItems) {
        this.mItems = mItems;
    }

    @Override
    public int getItemViewType(int position) {
        // 1.1 첫번째 위치는 ViewPager로 표현하기 위해 타입 설정
        if (position < mItems.populUsers.size())
            return Const.VIEW_TYPE_POPULAR_USER;
        else if (position < mItems.populUsers.size() + mItems.populPosts.size())
            return Const.VIEW_TYPE_POPULAR_POST;
        else
            return Const.VIEW_TYPE_NEW_POST;
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case Const.VIEW_TYPE_POPULAR_USER:
                ItemUserBinding populUserBinding = DataBindingUtil.inflate(inflater, R.layout.item_user, parent,false);
                ListHolder<ItemUserBinding, UserViewModel> userHolder = new ListHolder<>(populUserBinding, viewType);
                return userHolder;

            case Const.VIEW_TYPE_POPULAR_POST:
                ItemPostBinding populPostBinding = DataBindingUtil.inflate(inflater, R.layout.item_post, parent, false);
                ListHolder<ItemPostBinding, PostViewModel> pPostHolder = new ListHolder<>(populPostBinding, viewType);
                return pPostHolder;

            case Const.VIEW_TYPE_NEW_POST:
                ItemPostBinding newPostBinding = DataBindingUtil.inflate(inflater, R.layout.item_post, parent, false);
                ListHolder<ItemPostBinding, PostViewModel> nPostHolder = new ListHolder<>(newPostBinding, viewType);
                return nPostHolder;
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {
        // 1.1 첫번째 위치는 ViewPager로 표현하기 위해 타입 설정
        if (position < mItems.populUsers.size()) {
            uVmodel = mItems.populUsers.get(position);
            holder.bind(uVmodel);
        } else if (position < mItems.populUsers.size() + mItems.populPosts.size()) {
            pVModel = mItems.populPosts.get(position-mItems.populUsers.size());
            holder.bind(pVModel);
        } else {
            pVModel = mItems.newPosts.get(position-mItems.populUsers.size()-mItems.populPosts.size());
            holder.bind(pVModel);
        }
    }

    @Override
    public int getItemCount() {
        if (mItems == null)
            return 0;
        else {
            return mItems.populUsers.size() + mItems.newPosts.size() + mItems.populPosts.size();
        }
    }

    class ListHolder<E extends ViewDataBinding, F extends IModelGettable> extends RecyclerView.ViewHolder {
        private E mBinding;
        private int mViewType;

        public ListHolder(E binding, int viewType) {
            super(binding.getRoot());
            mBinding = binding;
            mViewType = viewType;
        }

        // 실질적인 데이터 설정
        public void bind(@NonNull F viewModel) {
            switch (mViewType) {
                case Const.VIEW_TYPE_POPULAR_USER:
                    ((ItemUserBinding)mBinding).setModel((User)viewModel.getModel());
                    break;
                case Const.VIEW_TYPE_POPULAR_POST:
                    ((ItemPostBinding)mBinding).setModel((Post)viewModel.getModel());
                    break;
                case Const.VIEW_TYPE_NEW_POST:
                    ((ItemPostBinding)mBinding).setModel((Post)viewModel.getModel());
                    break;
            }

            mBinding.executePendingBindings();
        }
    }
}
