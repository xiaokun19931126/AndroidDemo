package com.xiaokun.xiusou.demo6.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.xiaokun.xiusou.demo6.R;
import com.yuyh.library.view.progress.DonutProgress;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiaokun on 2017/7/6.
 */

public class DialogActivity extends AppCompatActivity
{
    @Bind(R.id.progressBar)
    DonutProgress progressBar;
    @Bind(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);


//        LoadingDialog loadingDialog = new LoadingDialog(this, "玩命加载中...");
//        loadingDialog.show();
    }

    @OnClick(R.id.btn)
    public void onViewClicked()
    {
        start();
    }

    Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case 0:
                    progressBar.setProgress(msg.arg1);
                    break;
                default:

                    break;
            }
        }
    };

    private void start()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for (int i = 1; i < 101; i++)
                {
                    try
                    {
                        Message msg = Message.obtain();
                        msg.what = 0;
                        msg.arg1 = i;
                        mHandler.sendMessage(msg);
                        Thread.sleep(100);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
