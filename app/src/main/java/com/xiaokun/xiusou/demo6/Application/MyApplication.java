package com.xiaokun.xiusou.demo6.Application;

import android.app.Application;

import com.socks.library.KLog;
import com.xiaokun.xiusou.demo6.BuildConfig;
import com.xiaokun.xiusou.demo6.Utils.ACache;
import com.xiusou.xiusoukey.RegisterTool;
import com.yuyh.library.AppUtils;
import com.yuyh.library.utils.toast.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/11/18 0018.
 */

public class MyApplication extends Application {

    public static ACache aCache;
    public static ToastUtils toastUtils;
    private OkHttpClient okHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this);
        KLog.init(BuildConfig.LOG_DEBUG, "xiaocaiwudi");
        aCache = ACache.get(this);
        toastUtils = new ToastUtils();
//        RegisterTool.init(this,"1234567");
        //配置ssl参数，主要是sslSocketFactory和trustManager
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        //其他配置
        okHttpClient = new OkHttpClient.Builder()
                //其他配置
                .hostnameVerifier(new HostnameVerifier()
                {
                    @Override
                    public boolean verify(String hostname, SSLSession session)
                    {
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
