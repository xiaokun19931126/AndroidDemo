package com.xiaokun.xiusou.aidl.holder;

import android.view.View;
import android.widget.Button;

import com.xiaokun.adapter_library.BaseRecyclerAdapter;
import com.xiaokun.adapter_library.BaseViewHolder;
import com.xiaokun.xiusou.aidl.Bean.FuncHeadData;
import com.xiaokun.xiusou.aidl.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/17
 *     描述   :
 *     版本   : 1.0
 * </pre>
 */

public class FunctionHeadHolder extends BaseViewHolder<FuncHeadData>
{

    @Bind(R.id.check_car)
    Button checkCar;
    @Bind(R.id.check_mock_point)
    Button checkMockPoint;
    @Bind(R.id.case_function)
    Button caseFunction;
    @Bind(R.id.issue_case)
    Button issueCase;

    public FunctionHeadHolder(View itemView, BaseRecyclerAdapter baseRecyclerAdapter)
    {
        super(itemView, baseRecyclerAdapter);
    }

    @Override
    public int getContentViewId()
    {
        return R.layout.function_head;
    }

    @Override
    public void initViews()
    {
        super.initViews();
        ButterKnife.bind(this, mItemView);
    }

    @Override
    public void updateItem(FuncHeadData data, int position)
    {

    }

    @OnClick({R.id.check_car, R.id.check_mock_point, R.id.case_function, R.id.issue_case})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.check_car:
                break;
            case R.id.check_mock_point:
                break;
            case R.id.case_function:
                break;
            case R.id.issue_case:
                break;
        }
    }
}
