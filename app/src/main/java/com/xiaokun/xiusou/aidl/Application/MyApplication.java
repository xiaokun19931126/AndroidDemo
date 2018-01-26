package com.xiaokun.xiusou.aidl.Application;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.socks.library.KLog;
import com.wanjian.sak.SAK;
import com.xiaokun.http_library.RxHttpUtils;
import com.xiaokun.xiusou.aidl.BuildConfig;
import com.xiaokun.xiusou.aidl.Utils.ACache;
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

public class MyApplication extends MultiDexApplication
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
        SAK.init(this);
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
        RxHttpUtils.init(this);

        RxHttpUtils.getInstance()
                .config()
                .setBaseUrl("http://gank.io/api/")
                .setWriteTimeOut(10)
                .setConnectTimeOut(10)
                .setReadTimeOut(10)
                .setLog(BuildConfig.DEBUG);
    }

    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
