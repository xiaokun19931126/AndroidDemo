package com.xiaokun.xiusou.demo6.OKCallback;

import com.google.gson.Gson;
import com.socks.library.KLog;
import com.xiaokun.xiusou.demo6.Bean.DownLoadListData;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/12/26 0026.
 */

public abstract class DownLoadListDataCallback extends Callback<DownLoadListData> {

    @Override
    public DownLoadListData parseNetworkResponse(Response response, int id) throws Exception {
        String string = response.body().string();
        KLog.json(string);
        DownLoadListData downLoadListData = new Gson().fromJson(string, DownLoadListData.class);
        return downLoadListData;
    }
}
