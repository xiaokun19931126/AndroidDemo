package com.xiaokun.xiusou.demo6.Application;

import android.app.Application;

import com.socks.library.KLog;
import com.xiaokun.xiusou.demo6.BuildConfig;
import com.xiaokun.xiusou.demo6.Utils.ACache;
import com.yuyh.library.AppUtils;
import com.yuyh.library.utils.data.PrefsUtils;
import com.yuyh.library.utils.toast.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import io.flowup.FlowUp;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/11/18 0018.
 */

public class MyApplication extends Application
{

    public static ACache aCache;
    public static ToastUtils toastUtils;
    public static PrefsUtils mPref;
    private OkHttpClient okHttpClient;

    @Override
    public void onCreate()
    {
        super.onCreate();
        AppUtils.init(this);
        KLog.init(BuildConfig.LOG_DEBUG, "xiaocaiwudi");
        aCache = ACache.get(this);
        toastUtils = new ToastUtils();
        mPref = new PrefsUtils(this, "app_data");
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
        FlowUp.Builder.with(this)
                .apiKey("9a259c70bd634236ad1669e7271d7cd8")
                .forceReports(BuildConfig.DEBUG)
                .start();
    }

}
