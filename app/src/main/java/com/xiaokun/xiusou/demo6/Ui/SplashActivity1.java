package com.xiaokun.xiusou.demo6.Ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.xiaokun.xiusou.demo6.Activity.FirstActivity;
import com.xiaokun.xiusou.demo6.R;
import com.yuyh.library.Base.BaseActivity;

import butterknife.Bind;

/**
 * Created by xiaokun on 2017/7/26.
 */

public class SplashActivity1 extends BaseActivity
{

    @Bind(R.id.linear)
    LinearLayout linear;

    private int mDelayTime = 1500;

    private Runnable mGotoMainRunnable = new Runnable()
    {
        public void run()
        {
            Intent localIntent = new Intent(SplashActivity1.this, FirstActivity.class);
            SplashActivity1.this.startActivity(localIntent);
            SplashActivity1.this.finish();
//            SplashActivity.this.overridePendingTransition(0, 0);
        }
    };

    protected void onPause()
    {
        super.onPause();
        getWindow().getDecorView().removeCallbacks(this.mGotoMainRunnable);
    }

    protected void onResume()
    {
        super.onResume();
        getWindow().getDecorView().postDelayed(this.mGotoMainRunnable, this.mDelayTime);
    }

    @Override
    public void initData(Bundle bundle)
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_splash;
    }

    @Override
    protected void initPresenter()
    {

    }

    @Override
    public void initView()
    {
        linear.setBackground(getResources().getDrawable(R.drawable.color_blue));
    }

    @Override
    public void doBusiness(Context context)
    {

    }
}
