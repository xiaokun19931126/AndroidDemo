package com.xiaokun.adapter_library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/10
 *     描述   : RecyclerView.ViewHolder包装类
 *     版本   : 1.0
 * </pre>
 */

public abstract class BaseViewHolder<T extends BaseAdapterData> extends RecyclerView.ViewHolder implements UIInit
{
    protected BaseRecyclerAdapter mBaseAdapter;
    protected Context mContext;
    private LayoutInflater mLayoutInflater;
    protected View mItemView;

    private void setUIContext(Context context)
    {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    protected LayoutInflater getLayoutInflater()
    {
        return mLayoutInflater;
    }

    protected Context getContext()
    {
        return mContext;
    }


    public BaseViewHolder(View itemView, BaseRecyclerAdapter baseRecyclerAdapter)
    {
        super(itemView);
        this.mItemView = itemView;
        mBaseAdapter = baseRecyclerAdapter;
//        ButterKnife.bind(this, itemView);
        setUIContext(itemView.getContext());
        initBeforeView();
        initViews();
    }

    @Override
    public void initBeforeView()
    {

    }

    @Override
    public void initViews()
    {
        //可以在子类中使用ButterKnife
        //ButterKnife.bind(this,mItemView);
    }

    public View getItemView()
    {
        return mItemView;
    }

    public abstract void updateItem(T data, int position);
}
