package com.xiaokun.xiusou.demo6.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import com.socks.library.KLog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/12/10 0010.
 */
public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //接收安装广播
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            String packageName = intent.getDataString();
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context
                    .TELEPHONY_SERVICE);
            KLog.d(packageName);
            if (packageName.equals("package:com.xiaokun.xiusou.demo66")) {
                //这里可以加一次安装数
                OkHttpUtils.post()
                        .addParams("uid", "522414716")
                        .addParams("appkey", "123456")
                        .addParams("baoming", "com.xiaokun.xiusou.demo66")
                        .addParams("deviceid", tm.getDeviceId()).url("http://z.01808" +
                        ".cn/api/install/").build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });
            }
        }
        //接收卸载广播
//        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
//            String packageName = intent.getDataString();
//            Log.d("xiao", "卸载了:" + packageName + "包名的程序");
//            if (packageName.contains("xiusou")){
//                Toast.makeText(context,"秀搜被卸载了",Toast.LENGTH_SHORT).show();
//            }
//        }
    }
}
