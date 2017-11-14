package com.xiaokun.xiusou.demo6.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonHolder> {

    private List<T> mDatas;
    private Context mContext;
    private int mLayoutId;

    public CommonAdapter(List<T> mDatas, Context mContext, int layoutId) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        this.mLayoutId = layoutId;
    }

    @Override
    public CommonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonHolder.getHolder(mContext, parent, mLayoutId);
    }

    @Override
    public void onBindViewHolder(CommonHolder holder, int position) {
        holder.updatePosition(position);
        convert(holder,mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public abstract void convert(CommonHolder holder, T t);
}
