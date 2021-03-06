package com.xiaokun.xiusou.aidl.CustomView;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaokun.xiusou.aidl.R;

/**
 * Created by xiaokun on 2017/7/6.
 * dialog包装类
 */

public class LoadingDialog
{

    private CircleRingView ringView;
    private Dialog dialog;

    public LoadingDialog(Context context, String msg)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.loading_dialog_view, null);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.dialog_view);
        ringView = (CircleRingView) view.findViewById(R.id.lv_circularring);
        TextView loadView = (TextView) view.findViewById(R.id.loading_text);
        loadView.setText(msg);
        dialog = new Dialog(context, R.style.loading_dialog);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ringView.startAnimation();
    }

    public void show()
    {
        dialog.show();

    }

    public void hide()
    {
        if (dialog != null)
        {
            ringView.stopAnimation();
            dialog.dismiss();
            dialog = null;
        }
    }
}
