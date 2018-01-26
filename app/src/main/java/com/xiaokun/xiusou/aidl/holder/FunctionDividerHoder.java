package com.xiaokun.xiusou.aidl.holder;

import android.view.View;

import com.xiaokun.adapter_library.BaseRecyclerAdapter;
import com.xiaokun.adapter_library.BaseViewHolder;
import com.xiaokun.xiusou.aidl.Bean.FuncDivData;
import com.xiaokun.xiusou.aidl.R;

import butterknife.ButterKnife;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/17
 *     描述   :
 *     版本   : 1.0
 * </pre>
 */

public class FunctionDividerHoder extends BaseViewHolder<FuncDivData>
{
    public FunctionDividerHoder(View itemView, BaseRecyclerAdapter baseRecyclerAdapter)
    {
        super(itemView, baseRecyclerAdapter);
    }

    @Override
    public int getContentViewId()
    {
        return R.layout.function_div;
    }

    @Override
    public void initViews()
    {
        super.initViews();
        ButterKnife.bind(this, mItemView);
    }

    @Override
    public void updateItem(FuncDivData data, int position)
    {

    }
}
