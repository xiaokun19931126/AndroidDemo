package com.xiaokun.xiusou.aidl.OKCallback;

import com.google.gson.Gson;
import com.socks.library.KLog;
import com.xiaokun.xiusou.aidl.Bean.FangZakerData;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/27 0027.
 */

public abstract class FangZakerDataCallback extends Callback<FangZakerData> {

    @Override
    public FangZakerData parseNetworkResponse(Response response, int id) throws
            Exception {
        String string = response.body().string();
        KLog.json(string);
        FangZakerData fangZakerData = new Gson().fromJson(string, FangZakerData.class);
        return fangZakerData;
    }

}
