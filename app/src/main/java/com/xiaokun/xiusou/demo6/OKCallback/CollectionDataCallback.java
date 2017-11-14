package com.xiaokun.xiusou.demo6.OKCallback;

import com.google.gson.Gson;
import com.xiaokun.xiusou.demo6.Bean.CollectionData;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/12/24 0024.
 */

public abstract class CollectionDataCallback extends Callback<CollectionData> {
    @Override
    public CollectionData parseNetworkResponse(Response response, int id) throws Exception {
        String string = response.body().string();
        CollectionData collectionData = new Gson().fromJson(string, CollectionData.class);
        return collectionData;
    }
}
