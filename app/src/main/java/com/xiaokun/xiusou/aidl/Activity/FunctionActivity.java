package com.xiaokun.xiusou.aidl.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xiaokun.adapter_library.BaseAdapterData;
import com.xiaokun.adapter_library.BaseRecyclerAdapter;
import com.xiaokun.xiusou.aidl.Bean.FuncDivData;
import com.xiaokun.xiusou.aidl.Bean.FuncHeadData;
import com.xiaokun.xiusou.aidl.Bean.FuncTvData;
import com.xiaokun.xiusou.aidl.R;
import com.xiaokun.xiusou.aidl.holder.FunctionDividerHoder;
import com.xiaokun.xiusou.aidl.holder.FunctionHeadHolder;
import com.xiaokun.xiusou.aidl.holder.FunctionTvHolder;
import com.yuyh.library.Base.BaseActivity;

import butterknife.Bind;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/17
 *     描述   :
 *     版本   : 1.0
 * </pre>
 */

public class FunctionActivity extends BaseActivity
{
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private BaseRecyclerAdapter adapter;

    @Override
    public void initData(Bundle bundle)
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_function;
    }

    @Override
    protected void initPresenter()
    {

    }

    @Override
    public void initView()
    {
        initRecyclerView();
    }

    private void initRecyclerView()
    {
        adapter = new BaseRecyclerAdapter(this);
        final GridLayoutManager manager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(manager);

        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
        {
            @Override
            public int getSpanSize(int position)
            {
                BaseAdapterData data = adapter.getData().get(position);
                if (data instanceof FuncHeadData || data instanceof FuncDivData || data instanceof FuncTvData)
                {
                    return manager.getSpanCount();
                } else
                {
                    return 1;
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void doBusiness(Context context)
    {
        adapter.registerHolder(FunctionHeadHolder.class, new FuncHeadData());
        adapter.registerHolder(FunctionDividerHoder.class, new FuncDivData());
        adapter.registerHolder(FunctionTvHolder.class, new FuncTvData());
    }
}
