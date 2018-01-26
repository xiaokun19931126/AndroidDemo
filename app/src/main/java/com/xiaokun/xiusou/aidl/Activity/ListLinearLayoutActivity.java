package com.xiaokun.xiusou.aidl.Activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xiaokun.xiusou.aidl.Application.MyApplication;
import com.xiaokun.xiusou.aidl.Bean.TestData;
import com.xiaokun.xiusou.aidl.CustomView.LinearLayoutAdapter;
import com.xiaokun.xiusou.aidl.CustomView.ListLinearLayout;
import com.xiaokun.xiusou.aidl.R;
import com.yuyh.library.AppUtils;
import com.yuyh.library.Base.BaseActivity;
import com.yuyh.library.utils.DimenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @author xiaokun
 * @date 2017/11/13
 */

public class ListLinearLayoutActivity extends BaseActivity
{
    private static final String CLICK_POSITION = "click_position";
    @Bind(R.id.line_layout)
    ListLinearLayout lineLayout;
    @Bind(R.id.btn_refresh)
    Button btnRefresh;
    private MyAdapter adapter;

    @Override
    public void initData(Bundle bundle)
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_list_linear_layout;
    }

    @Override
    protected void initPresenter()
    {

    }

    @Override
    public void initView()
    {
        lineLayout.setPaddingLeft(DimenUtils.dpToPxInt(10));
        lineLayout.setPaddingRight(DimenUtils.dpToPxInt(10));
        TestData testData = new Gson().fromJson(data2, TestData.class);
        List<TestData.DataBean> data = testData.getData();
        adapter = new MyAdapter(R.layout.item_test_linear_layout, data);
        lineLayout.setAdapter(adapter);
    }

    @Override
    public void doBusiness(Context context)
    {
        List<String> stringList = new ArrayList<>();
        stringList.add("你好");
        stringList.add("你好");
        stringList.add("你好");
        stringList.add("你好");
        stringList.add("测试");
        stringList.add("测试");
        stringList.add("测试");
        stringList.add("测试");
        stringList.add("测试");
        stringList.add("测试");
        stringList.add("测试");
        Log.e("ListLinearLayout", "doBusiness(ListLinearLayoutActivity.java:77)" + stringList.toString());
    }

    @OnClick(R.id.btn_refresh)
    public void onViewClicked()
    {
        MyApplication.mPref.putInt(CLICK_POSITION, -1);
        TestData testData = new Gson().fromJson(data1, TestData.class);
        List<TestData.DataBean> data = testData.getData();
        adapter.setNewData(data);
        lineLayout.setAdapter(adapter);
    }

    private void refresh()
    {
        TestData testData = new Gson().fromJson(data2, TestData.class);
        List<TestData.DataBean> data = testData.getData();
        adapter.setNewData(data);
        lineLayout.setAdapter(adapter);
    }

    class MyAdapter extends LinearLayoutAdapter<TestData.DataBean>
    {

        public MyAdapter(int itemLayoutId, List<TestData.DataBean> data)
        {
            super(itemLayoutId, data);
        }

        @Override
        public View getView(final int position)
        {
            View view = View.inflate(AppUtils.getAppContext(), getmItemLayoutId(), null);
            TextView btn = (TextView) view.findViewById(R.id.text_view);
//            View view = holder.getItemView();
//            TextView btn = holder.getView(R.id.text_view);
            btn.setText(getData().get(position).getName());
            int clickPosition = MyApplication.mPref.getInt(CLICK_POSITION, -1);
            if (clickPosition != -1 && clickPosition == position)
            {
                view.setBackgroundColor(getResources().getColor(R.color.grey));
            }
            view.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    MyApplication.mPref.putInt(CLICK_POSITION, position);
                    refresh();
                    Toast.makeText(AppUtils.getAppContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
            return view;
        }
    }

    private String data = "{\n" +
            "  \"Data\": [\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private String data1 = "{\n" +
            "  \"Data\": [\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private String data2 = "{\n" +
            "  \"Data\": [\n" +
            "    {\n" +
            "      \"name\": \"依迅1\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅2\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅3\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅4\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅5\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅6\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅7\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅8\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅9\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅10\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅11\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅12\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅13\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";
}
