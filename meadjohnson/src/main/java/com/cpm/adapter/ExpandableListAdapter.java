package com.cpm.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cpm.GetterSetter.NavMenuItemGetterSetter;
import com.cpm.capitalfoods.R;
import com.cpm.dailyentry.AssetActivity;
import com.cpm.dailyentry.ClosingStock;
import com.cpm.dailyentry.MidDayStock;
import com.cpm.dailyentry.OpeningStock;
import com.cpm.dailyentry.PromotionActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yadavendras on 15-12-2015.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<NavMenuItemGetterSetter> mListDataHeader; // header titles

    // child data in format of header title, child title
    private HashMap<NavMenuItemGetterSetter, List<String>> mListDataChild;
    ExpandableListView expandList;

    public ExpandableListAdapter(Context context, List<NavMenuItemGetterSetter> listDataHeader, HashMap<NavMenuItemGetterSetter, List<String>> listChildData, ExpandableListView mView) {
        this.mContext = context;
        this.mListDataHeader = listDataHeader;
        this.mListDataChild = listChildData;
        this.expandList = mView;
    }

    @Override
    public int getGroupCount() {
        int i = mListDataHeader.size();
        Log.d("GROUPCOUNT", String.valueOf(i));
        return this.mListDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int childCount = 0;
        if (groupPosition == 0) {
            childCount = this.mListDataChild.get(this.mListDataHeader.get(groupPosition))
                    .size();
        }
        return childCount;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.mListDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Log.d("CHILD", mListDataChild.get(this.mListDataHeader.get(groupPosition))
                .get(childPosition).toString());
        return this.mListDataChild.get(this.mListDataHeader.get(groupPosition))
                .get(childPosition);
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        NavMenuItemGetterSetter headerTitle = (NavMenuItemGetterSetter) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.listheader, null);
        }
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.submenu);
        ImageView headerIcon = (ImageView) convertView.findViewById(R.id.iconimage);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle.getIconName());
        headerIcon.setImageResource(headerTitle.getIconImg());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_sub_menu, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.submenu);

        txtListChild.setText(childText);

        txtListChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(childText.equals("Opening Stock")){

                    Intent in = new Intent(mContext, OpeningStock.class);
                    mContext.startActivity(in);
                }
                else if(childText.equals("Midday Stock")){

                    Intent in = new Intent(mContext, MidDayStock.class);
                    mContext.startActivity(in);

                }else if(childText.equals("Closing Stock")){

                    Intent in = new Intent(mContext, ClosingStock.class);
                    mContext.startActivity(in);

                }else if(childText.equals("Promotion")){

                    Intent in = new Intent(mContext, PromotionActivity.class);
                    mContext.startActivity(in);

                }else if(childText.equals("Asset")){

                    Intent in = new Intent(mContext, AssetActivity.class);
                    mContext.startActivity(in);

                }
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
