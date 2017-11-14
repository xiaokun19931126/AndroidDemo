package com.xiaokun.xiusou.demo6.Ui.XsNews;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.xiaokun.xiusou.demo6.R;
import com.yuyh.library.Base.BaseActivity;

import butterknife.Bind;

/**
 * Created by xiaokun on 2017/7/26.
 */

public class XsNewsActivity extends BaseActivity
{
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.SwipeRefreshLayout)
    android.support.v4.widget.SwipeRefreshLayout SwipeRefreshLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void initData(Bundle bundle)
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter()
    {

    }

    @Override
    public void initView()
    {

    }

    @Override
    public void doBusiness(Context context)
    {

    }

}
