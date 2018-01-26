package com.xiaokun.xiusou.aidl.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.xiaokun.xiusou.aidl.Application.MyApplication;
import com.xiaokun.xiusou.aidl.CustomView.RoundIndicatorView;
import com.xiaokun.xiusou.aidl.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class CustomViewActivity extends AppCompatActivity {
    @Bind(R.id.my_view)
    RoundIndicatorView roundIndicatorView;
    @Bind(R.id.edit)
    EditText editText;
    @Bind(R.id.btn)
    Button button;
    @Bind(R.id.activity_main)
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        ButterKnife.bind(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.valueOf(editText.getText().toString());
                roundIndicatorView.setCurrentNumAnim(a);
            }
        });
    }

    private void reCreate() {
        MyApplication.toastUtils.showToast("hah");
//        setContentView(R.layout.activity_custom);
    }

}
