package com.xiaokun.xiusou.demo6.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.socks.library.KLog;
import com.xiaokun.xiusou.demo6.Bean.DownLoadListData;
import com.xiaokun.xiusou.demo6.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/12/24 0024.
 */

public class DownTaskActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Context mContext;
    private LinearLayoutManager linearLayoutManager;
    private List<DownLoadListData.DownLoadListDetail> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_task);
        mContext = this;

        recyclerView = (RecyclerView) findViewById(R.id.task_recyclerview);
        linearLayoutManager = new LinearLayoutManager(mContext);

//        OkHttpUtils.get().url("https://z.01808.cn/api/yingyongliebiao/").build().execute(new DownLoadListDataCallback() {
//
//            @Override
//            public void onError(Call call, Exception e, int id) {
//            }
//
//            @Override
//            public void onResponse(DownLoadListData response, int id) {
//                list = response.getList();
//                recyclerView.setLayoutManager(linearLayoutManager);
//                TaskDownAdapter taskDownAdapter = new TaskDownAdapter(mContext, list);
//                recyclerView.setAdapter(taskDownAdapter);
//                recyclerView.addItemDecoration(new DividerItemDecoration(mContext,
//                        DividerItemDecoration.VERTICAL_LIST));
//                recyclerView.setItemAnimator(new DefaultItemAnimator());
//            }
//        });

        OkHttpUtils.get().url("https://z.01808.cn/api/android/").build().execute(new StringCallback() {


            @Override
            public void onError(Call call, Exception e, int id) {
                KLog.json(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                KLog.json(response);
            }
        });


    }
}
