package com.xiaokun.adapter_library;

import android.view.View;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/10
 *     描述   : 空数据viewholder类
 *     版本   : 1.0
 * </pre>
 */

public class NoDataViewHolder extends BaseViewHolder
{
    public NoDataViewHolder(View itemView, BaseRecyclerAdapter baseRecyclerAdapter)
    {
        super(itemView, baseRecyclerAdapter);
    }

    @Override
    public void updateItem(BaseAdapterData Data, int position)
    {

    }

    @Override
    public int getContentViewId()
    {
        return 0;
    }

    @Override
    public void initViews()
    {

    }
}
