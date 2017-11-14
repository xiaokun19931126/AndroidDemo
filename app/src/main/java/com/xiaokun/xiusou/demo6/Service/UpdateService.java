package com.xiaokun.xiusou.demo6.Service;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.socks.library.KLog;
import com.xiaokun.xiusou.demo6.Activity.NotifacationUpadateActivity;
import com.xiaokun.xiusou.demo6.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/2/23.
 */

public class UpdateService extends Service
{
    private Handler mHandler = new Handler()
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
                    builder.setProgress(100, progress, false)
                            .setContentText("进度：" + progress + "%");
                    manager.notify(0, builder.build());
                    break;
                case 2:
                    break;
            }
        }
    };
    private Context mContext;
    private NotificationManager manager;
    private Notification.Builder builder;
    private int progress;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mBinder = new DownloadBinder();
        mContext = this;
        manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }

    public DownloadBinder mBinder;

    public class DownloadBinder extends Binder
    {
        /**
         * 最后的进度
         */
        int lastRate = 0;

        public void Download(String url, Handler handler)
        {
            setNotification();
            down(url, handler);
        }

        private void setNotification()
        {
            builder = new Notification.Builder(mContext);
            Intent intent = new Intent(mContext, NotifacationUpadateActivity.class);
            /**click to intent to the Activity*/
            PendingIntent activity = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setWhen(System.currentTimeMillis())
                    .setOngoing(true)
                    .setContentIntent(activity)
                    .setPriority(Notification.PRIORITY_DEFAULT)
                    .setSmallIcon(R.drawable.search_icon)
                    .setContentTitle("等待下载")
                    .setContentText("进度：")
                    .setTicker("开始下载...")
                    .setProgress(100, progress, false);
            manager.notify(0, builder.build());
        }

        private void down(final String url, final Handler handler)
        {

            UpdateService.this.mHandler = handler;
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    final HttpURLConnection conn;
                    try
                    {
                        KLog.d("url:" + url);
                        conn = (HttpURLConnection) new URL(url).openConnection();
                        conn.connect();
                        int contentLength = conn.getContentLength();
                        KLog.d("contentLength:" + contentLength);
                        InputStream inputStream = conn.getInputStream();
                        int read;
                        int count = 0;
                        progress = 0;
                        byte[] bytes = new byte[1024];//1KB
                        while ((read = inputStream.read(bytes)) != -1)
                        {
                            count += read;
                            progress = (int) ((float) count / contentLength * 100);
                            if (progress == lastRate + 5)
                            {
                                Message message = mHandler.obtainMessage();
                                message.what = 1;
                                message.obj = progress;
//                                mHandler.obtainMessage(0, progress).sendToTarget();
                                mHandler.sendMessage(message);
                                lastRate = progress;
                                KLog.d("lastRate:" + lastRate);
                            }
                        }
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }


}
