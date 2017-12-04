package com.heepie.soundhub.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.heepie.soundhub.BR;
import com.heepie.soundhub.R;
import com.heepie.soundhub.databinding.DetailListGroupBinding;
import com.heepie.soundhub.domain.model.Post;
import com.heepie.soundhub.viewmodel.PostViewModel;

/**
 * Created by Heepie on 2017. 12. 1..
 */

/*public class ExpandListAdapter extends BaseExpandableListAdapter {
    private ObservableArrayList<Post> groups;

    public void setGroup(PostViewModel groups) {
        this.groups = groups.getModel();
    }

    // Parent 설정
    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.detail_list_group, parent, false);
            DetailListGroupBinding groupBinding = DataBindingUtil.inflate(inflater, R.layout.detail_list_group, parent, false);
            groupBinding.setVariable(BR.model, groups.get(groupPosition));

            groupBinding.setVariable(BR.model, groups.get(groupPosition));
            groupBinding.executePendingBindings();
        }

        return convertView;
    }



    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }




    // Child 설정
    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).getComment_tracks().size();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).getComment_tracks().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.detail_list_child, parent, false);
            DetailListGroupBinding groupBinding = DataBindingUtil.inflate(inflater, R.layout.detail_list_child, parent, false);
            groupBinding.setVariable(BR.model, groups.get(groupPosition).getComment_tracks().get(childPosition));

            groupBinding.setVariable(BR.model, groups.get(groupPosition).getComment_tracks().get(childPosition));
            groupBinding.executePendingBindings();
        }

        return convertView;
    }
}*/
