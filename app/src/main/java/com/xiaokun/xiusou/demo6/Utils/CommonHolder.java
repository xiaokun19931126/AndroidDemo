package com.xiaokun.xiusou.demo6.Utils;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 肖坤 on 2017/3/11
 * ListView和RecyclerView的通用Holder
 */

public class CommonHolder extends RecyclerView.ViewHolder{

    private Context mContext;
    private View convertView;
    private SparseArrayCompat<View> mViews;
    private int position;

    private CommonHolder(Context context, View itemView, ViewGroup parent) {
        super(itemView);
        this.mContext = context;
        this.convertView = itemView;
        mViews = new SparseArrayCompat<View>();
    }

    /**
     * 入口方法
     *
     * @param context
     * @param parent
     * @param layoutId
     * @return
     */
    public static CommonHolder getHolder(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        CommonHolder holder = new CommonHolder(context, itemView, parent);
        return holder;
    }

    /**
     * 通过SparseArray方法来缓存view
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public void updatePosition(int position) {
        this.position = position;
    }
}
