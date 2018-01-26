package com.xiaokun.xiusou.aidl.Activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xiaokun.xiusou.aidl.Bean.Download;
import com.xiaokun.xiusou.aidl.R;
import com.xiaokun.xiusou.aidl.Service.DownloadService;
import com.yuyh.library.Base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @author xiaokun
 * @date 2017/12/7
 */

public class RetrofitDownLoadActivity extends BaseActivity
{

    public static final String MESSAGE_PROGRESS = "message_progress ";
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Bind(R.id.btn_download)
    AppCompatButton btnDownload;
    @Bind(R.id.progress)
    ProgressBar mProgressBar;
    @Bind(R.id.progress_text)
    TextView mProgressText;

    @Override
    public void initData(Bundle bundle)
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_retrofit_download;
    }

    @Override
    protected void initPresenter()
    {

    }

    @Override
    public void initView()
    {
        registerReceiver();
    }

    private void registerReceiver()
    {
        LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MESSAGE_PROGRESS);
        bManager.registerReceiver(broadcastReceiver, intentFilter);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            if (intent.getAction().equals(MESSAGE_PROGRESS))
            {
                Download download = intent.getParcelableExtra("download");
                mProgressBar.setProgress(download.getProgress());
                if (download.getProgress() == 100)
                {
                    mProgressText.setText("File Download Complete");
                } else
                {
                    mProgressText.setText(String.format("Downloaded (%d/%d) MB",
                            download.getCurrentFileSize(), download.getTotalFileSize()));
                }
            }
        }
    };

    @Override
    public void doBusiness(Context context)
    {

    }

    @OnClick(R.id.btn_download)
    public void onViewClicked()
    {
        if (checkPermission())
        {
            startDownload();
        } else
        {
            requestPermission();
        }
    }

    private void startDownload()
    {
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
    }

    private boolean checkPermission()
    {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED)
        {
            return true;
        } else
        {
            return false;
        }
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        switch (requestCode)
        {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    startDownload();
                } else
                {
                    Snackbar.make(findViewById(R.id.coordinatorLayout), "Permission Denied, Please allow to proceed !", Snackbar.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

}
