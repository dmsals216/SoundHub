package com.heepie.soundhub.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.heepie.soundhub.R;
import com.heepie.soundhub.databinding.ItemPostBinding;
import com.heepie.soundhub.databinding.ItemUserBinding;
import com.heepie.soundhub.model.Post;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.utils.IModelGettable;
import com.heepie.soundhub.viewmodel.PostViewModel;
import com.heepie.soundhub.viewmodel.UserViewModel;

/**
 * Created by Heepie on 2017. 11. 25..
 */

/*public class RecyclerViewAdapter<T extends IModelGettable> extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {
    ObservableArrayList<T> mItems;

    public void setmItems(ObservableArrayList<T> mItems) {
        this.mItems = mItems;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (T instanceof UserViewModel) {
            ItemUserBinding itemUserBinding = DataBindingUtil.inflate(inflater, R.layout.item_user, parent,false);
            Holder<ItemUserBinding, UserViewModel> userHolder = new Holder<>(itemUserBinding);
        } else if (T instanceof PostViewModel) {
            ItemPostBinding itemPostBinding = DataBindingUtil.inflate(inflater, R.layout.item_post, parent, false);
            Holder<ItemPostBinding, PostViewModel> postHolder = new Holder<>(itemPostBinding);
        }

        switch (viewType) {
            case Const.VIEW_TYPE_POPULAR_USER:
                //
                ItemUserBinding itemUserBinding = DataBindingUtil.inflate(inflater, R.layout.item_user, parent,false);

                Holder<ItemUserBinding, PostViewModel> userHolder = new Holder<>(itemUserBinding, viewType);
                return userHolder;

            case Const.VIEW_TYPE_POPULAR_POST:
                ItemPostBinding itemPostBinding = DataBindingUtil.inflate(inflater, R.layout.item_post, parent, false);
                Holder<ItemPostBinding, PostViewModel> postHolder = new Holder<>(itemPostBinding, viewType);
                return postHolder;

        }

        return null;
    }

    // 3. Binding된 View에 해당 데이터 설정
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        T model = mItems.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        if (mItems == null)
            return 0;
        return mItems.size();
    }

    public class Holder<E extends ViewDataBinding, F extends IModelGettable> extends RecyclerView.ViewHolder {
        private E mBinding;
        private int mViewType;

        public Holder(E binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        // 실질적인 데이터 설정
        public void bind(@NonNull F viewModel) {
            *//*switch (mViewType) {
                case Const.VIEW_TYPE_POPULAR_POST:
                    ((ItemPostBinding)mBinding).setModel((Post)viewModel.getModel());
                    break;
                case Const.VIEW_TYPE_POPULAR_USER:
                    ((ItemUserBinding)mBinding).setModel((User)viewModel.getModel());
                    break;
            }*//*

            mBinding.executePendingBindings();
        }
    }
}*/
