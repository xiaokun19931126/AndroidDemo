package com.xiaokun.adapter_library;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/15
 *     描述   : 加载item的viewholder
 *     版本   : 1.0
 * </pre>
 */

public class LoadingViewHolder extends BaseViewHolder
{
    public static final int STATUS_LOADING = 1;
    public static final int STATUS_FAIL = 2;
    public static final int STATUS_END = 3;

    private LinearLayout mLoadMoreLoadingView;
    private ProgressBar mLoadingProgress;
    private TextView mLoadingText;
    private FrameLayout mLoadMoreLoadFailView;
    private TextView mTvPrompt;
    private FrameLayout mLoadMoreLoadEndView;

    public static int state = STATUS_LOADING;

    public LoadingViewHolder(View itemView, BaseRecyclerAdapter baseRecyclerAdapter)
    {
        super(itemView, baseRecyclerAdapter);
    }

    @Override
    public void initViews()
    {
        super.initViews();
        initView(mItemView);
        initListener();
    }

    @Override
    public int getContentViewId()
    {
        return R.layout.loading_view_adapter;
    }

    @Override
    public void updateItem(BaseAdapterData data, int position)
    {
        switch (state)
        {
            case STATUS_LOADING:
                loading();
                break;
            case STATUS_END:
                loadingEnd();
                break;
            case STATUS_FAIL:
                loadingFailed();
                break;
            default:

                break;
        }
    }

    private void initView(View mItemView)
    {
        mLoadMoreLoadingView = (LinearLayout) mItemView.findViewById(R.id.load_more_loading_view);
        mLoadingProgress = (ProgressBar) mItemView.findViewById(R.id.loading_progress);
        mLoadingText = (TextView) mItemView.findViewById(R.id.loading_text);
        mLoadMoreLoadFailView = (FrameLayout) mItemView.findViewById(R.id.load_more_load_fail_view);
        mTvPrompt = (TextView) mItemView.findViewById(R.id.tv_prompt);
        mLoadMoreLoadEndView = (FrameLayout) mItemView.findViewById(R.id.load_more_load_end_view);
    }

    private void initListener()
    {
        mTvPrompt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
    }

    /**
     * 正在加载中...
     */
    public void loading()
    {
        state = STATUS_LOADING;
        mLoadMoreLoadingView.setVisibility(View.VISIBLE);
        mLoadMoreLoadFailView.setVisibility(View.GONE);
        mLoadMoreLoadEndView.setVisibility(View.GONE);
    }

    /**
     * 加载失败...
     */
    public void loadingFailed()
    {
        state = STATUS_FAIL;
        mLoadMoreLoadingView.setVisibility(View.GONE);
        mLoadMoreLoadFailView.setVisibility(View.VISIBLE);
        mLoadMoreLoadEndView.setVisibility(View.GONE);
    }

    /**
     * 加载完成...
     */
    public void loadingEnd()
    {
        state = STATUS_END;
        mLoadMoreLoadingView.setVisibility(View.GONE);
        mLoadMoreLoadFailView.setVisibility(View.GONE);
        mLoadMoreLoadEndView.setVisibility(View.VISIBLE);
    }

}
