package com.heepie.soundhub.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.heepie.soundhub.BR;
import com.heepie.soundhub.R;
import com.heepie.soundhub.databinding.DetailListChildBinding;
import com.heepie.soundhub.databinding.DetailListGroupBinding;
import com.heepie.soundhub.domain.model.Comment_track;
import com.heepie.soundhub.domain.model.Post;
import com.heepie.soundhub.viewmodel.PostViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Heepie on 2017. 12. 1..
 */

public class ExpandListAdapter extends BaseExpandableListAdapter {
    private List<String> groups;
    private Map<String, List<Comment_track>> comments;

    public ExpandListAdapter(List<String> groups, Map<String, List<Comment_track>> comments) {
        this.groups = groups;
        this.comments = comments;
    }

    @Override
    public int getGroupCount() {
        // Get header size
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // return children count
        return comments.get(this.groups.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        // Get header position
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // This will return the child
        return comments.get(groups.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            DetailListGroupBinding groupBinding = DataBindingUtil.inflate(inflater, R.layout.detail_list_group, parent, false);
            groupBinding.setName((String)getGroup(groupPosition));

            return groupBinding.getRoot();
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            DetailListChildBinding childBinding = DataBindingUtil.inflate(inflater, R.layout.detail_list_child, parent, false);
            childBinding.setVariable(BR.model, getChild(groupPosition, childPosition));

            return childBinding.getRoot();
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
