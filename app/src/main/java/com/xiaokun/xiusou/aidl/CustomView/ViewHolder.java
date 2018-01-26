package com.xiaokun.xiusou.aidl.CustomView;

import android.util.SparseArray;
import android.view.View;

/**
 * @author xiaokun
 * @date 2017/11/15
 */

public class ViewHolder
{
    private View itemView;
    private SparseArray<View> mViews;

    public ViewHolder(View itemView)
    {
        this.itemView = itemView;
        mViews = new SparseArray<>();
    }

    public <T extends View> T getView(int resId)
    {
        View view = mViews.get(resId);
        if (view == null)
        {
            view = itemView.findViewById(resId);
            mViews.put(resId, view);
        }
        return (T) view;
    }

    public View getItemView()
    {
        return itemView;
    }

    public void setItemView(View itemView)
    {
        this.itemView = itemView;
    }
}
