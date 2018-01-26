package com.xiaokun.xiusou.aidl.holder;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaokun.adapter_library.BaseRecyclerAdapter;
import com.xiaokun.adapter_library.BaseViewHolder;
import com.xiaokun.xiusou.aidl.Bean.GankCategoryEntity;
import com.xiaokun.xiusou.aidl.R;
import com.xiaokun.xiusou.aidl.Ui.SplashActivity1;
import com.yuyh.library.AppUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/10
 *     描述   :
 *     版本   : 1.0
 * </pre>
 */

public class SearchViewHolder extends BaseViewHolder<GankCategoryEntity.ResultsBean>
{

    @Bind(R.id.category_desc)
    TextView categoryDesc;
    @Bind(R.id.category_author)
    TextView categoryAuthor;
    @Bind(R.id.category_date)
    TextView categoryDate;
    @Bind(R.id.home_cv)
    CardView homeCv;

    public SearchViewHolder(View itemView, BaseRecyclerAdapter baseRecyclerAdapter)
    {
        super(itemView, baseRecyclerAdapter);
    }

    @Override
    public int getContentViewId()
    {
        return R.layout.item_search_view;
    }

    @Override
    public void initViews()
    {
        super.initViews();
        ButterKnife.bind(this, mItemView);
    }

    @Override
    public void updateItem(GankCategoryEntity.ResultsBean data, int position)
    {
        int limit = 48;
        String string = data.getDesc();
        String text = string.length() > limit ? string.substring(0, limit) + "..." : string;

        categoryDesc.setText(text);
        categoryAuthor.setText(data.getWho());
        categoryDate.setText(data.getPublishedAt().substring(0, 10));
    }

    @OnClick(R.id.home_cv)
    public void onViewClicked()
    {
        Toast.makeText(mContext, "测试Application启动Activity", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(AppUtils.getAppContext(), SplashActivity1.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);
        AppUtils.getAppContext().startActivity(intent);
    }
}
