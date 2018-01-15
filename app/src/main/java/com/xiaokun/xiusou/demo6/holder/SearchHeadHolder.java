package com.xiaokun.xiusou.demo6.holder;

import android.view.View;
import android.widget.ImageView;

import com.xiaokun.adapter_library.BaseRecyclerAdapter;
import com.xiaokun.adapter_library.BaseViewHolder;
import com.xiaokun.xiusou.demo6.Bean.HeadViewData;
import com.xiaokun.xiusou.demo6.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/15
 *     描述   :
 *     版本   : 1.0
 * </pre>
 */

public class SearchHeadHolder extends BaseViewHolder<HeadViewData>
{
    @Bind(R.id.imageview)
    ImageView imageview;

    public SearchHeadHolder(View itemView, BaseRecyclerAdapter baseRecyclerAdapter)
    {
        super(itemView, baseRecyclerAdapter);
    }

    @Override
    public void initViews()
    {
        super.initViews();
        ButterKnife.bind(this, mItemView);
    }

    @Override
    public void updateItem(HeadViewData data, int position)
    {
        imageview.setImageResource(R.mipmap.gril);
    }

    @Override
    public int getContentViewId()
    {
        return R.layout.headview_girl_item;
    }


}
