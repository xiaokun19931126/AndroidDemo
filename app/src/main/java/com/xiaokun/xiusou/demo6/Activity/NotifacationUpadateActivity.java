package com.xiaokun.xiusou.demo6.Activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.socks.library.KLog;
import com.xiaokun.xiusou.demo6.R;
import com.xiaokun.xiusou.demo6.Service.UpdateService;

/**
 * Created by Administrator on 2017/2/23.
 * "http://softfile.3g.qq.com:8080/msoft/179/24659/43549/qq_hd_mini_1.4.apk
 */
public class NotifacationUpadateActivity extends AppCompatActivity
{
    //http://imtt.dd.qq.com/16891/88BF95CADA9CFDDC7E698F6B6744FCAD.apk?fsname=com.xiaocai.httpdemo_1.0_1.apk&csr=1bbd
    private static final String url = "http://14.204.74.141/imtt.dd.qq." +
            "com/16891/88BF95CADA9CFDDC7E698F6B6744FCAD.apk?mkey=58aec27075" +
            "da6ad8&f=6606&c=0&fsname=com.xiaocai.httpdemo_1.0_1.apk&csr=4d5s&p=.apk";

    Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case 0:
                    break;
                case 1:
                    int progress = (int) msg.obj;
                    KLog.d("progress:" + progress);
                    break;
                case 2:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
    }

    /**
     * 点击更新，启动后台服务
     */
    public void update(View v)
    {
        KLog.d("启动服务");
        Intent intent = new Intent(this, UpdateService.class);
        bindService(intent, new ServiceConnection()
        {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service)
            {
                mBinder = (UpdateService.DownloadBinder) service;
                mBinder.Download(url, mHandler);
            }

            @Override
            public void onServiceDisconnected(ComponentName name)
            {

            }
        }, BIND_AUTO_CREATE);
    }

    private UpdateService.DownloadBinder mBinder;
}
