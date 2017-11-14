package com.xiaokun.xiusou.demo6.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiaokun.xiusou.demo6.Adapter.CollectionAdapter;
import com.xiaokun.xiusou.demo6.Bean.CollectionData;
import com.xiaokun.xiusou.demo6.OKCallback.CollectionDataCallback;
import com.xiaokun.xiusou.demo6.R;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/12/24 0024.
 */

public class MyCollectionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Context mContext;
    private List<CollectionData.CollectionDetail> list = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private CollectionAdapter adapter;
    private int page = 1;
    private boolean isLoading = false;
    public static Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        mContext = this;

        recyclerView = (RecyclerView) findViewById(R.id.my_collection);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        getData("13886149842", "173f5e073b874052251f11d8922efa9016114a7d");
    }

    private void getData(String name, String key) {
        OkHttpUtils.post().addParams("username", name).addParams("key", key).addParams("page",
                page + "").url("https://z" + ".01808" + ".cn/api/myfavorite/").build()
                .execute(new CollectionDataCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(CollectionData response, int id) {
                        isLoading = false;
                        final List<CollectionData.CollectionDetail> mList = response.getList();
                        if (mList.size() > 0) {
                            updateView(mList);
                        } else {
                            if (list.size() == 0) {
                                //这里就提示没有收藏文章
                            } else {
                                adapter.notifyItemRemoved(adapter.getItemCount());
                            }
                        }
                    }
                });
    }

    /**
     * 更新UI
     *
     * @param mList
     */
    private void updateView(List<CollectionData.CollectionDetail> mList) {
        list.addAll(mList);
        if (page == 1) {//只有page=1时，会创建adapter
            adapter = new CollectionAdapter(mContext, MyCollectionActivity.this
                    .list, page);
            adapter.setOnItemClickListener(listener);//设置点击
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

        /**监听recyclerView滑动*/
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (linearLayoutManager.findLastVisibleItemPosition() == list.size()) {
                    if (!isLoading) {
                        isLoading = true;
                        handler.postDelayed(runnale, 1000);
                    }
                }
            }
        });
    }

    Runnable runnale = new Runnable() {
        @Override
        public void run() {
            page++;
            getData("13886149842",
                    "173f5e073b874052251f11d8922efa9016114a7d");
        }
    };

    CollectionAdapter.onItemClickListener listener = new CollectionAdapter.onItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Intent intent = new Intent();
            intent.setClass(MyCollectionActivity.this, WebActivity.class);
            startActivity(intent);
        }
    };
}
