package com.xiaokun.xiusou.demo6.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.xiaokun.xiusou.demo6.CustomView.MTextView;
import com.xiaokun.xiusou.demo6.R;

/**
 * Created by Administrator on 2017/1/5 0005.
 */

public class TextViewActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private MTextView textView4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview);

        textView1 = (TextView) findViewById(R.id.text_view1);
        textView2 = (TextView) findViewById(R.id.text_view2);
        textView3 = (TextView) findViewById(R.id.text_view3);
        textView4 = (MTextView) findViewById(R.id.text_view4);
        textView1.setTextSize(18);
        textView2.setTextSize(18);
        textView3.setTextSize(18);
        textView4.setTextSize(18);


        textView1.setText(Html.fromHtml("<font color=\"#AD6E13\" size=\"1\">使</font><font " +
                "color=\"#A98DDD\" size=\"4\">用</font><font color=\"#128E76\" " +
                "size=\"3\">h</font><font color=\"#3260F9\" size=\"7\">t</font><font " +
                "color=\"#4BF5ED\" size=\"4\">m</font><font color=\"#D6EB8F\" " +
                "size=\"4\">l</font><font color=\"#60C267\" size=\"3\">格</font><font " +
                "color=\"#794782\" size=\"1\">式</font><font color=\"#A3A840\" " +
                "size=\"2\">的</font><font color=\"#81DE65\" size=\"4\">优</font><font " +
                "color=\"#17CD06\" size=\"3\">点</font><font color=\"#BEE2A5\" " +
                "size=\"6\">是</font><font color=\"#A9D813\" size=\"3\">能</font><font " +
                "color=\"#337E68\" size=\"6\">更</font><font color=\"#034AF1\" " +
                "size=\"7\">改</font><font color=\"#77B206\" size=\"5\">t</font><font " +
                "color=\"#196CB8\" size=\"6\">e</font><font color=\"#EF4441\" " +
                "size=\"4\">x</font><font color=\"#96E40A\" size=\"1\">t</font><font " +
                "color=\"#9D1CE5\" size=\"1\">v</font><font color=\"#218648\" " +
                "size=\"5\">i</font><font color=\"#5DD7C8\" size=\"6\">e</font><font " +
                "color=\"#A04CAF\" size=\"5\">w</font><font color=\"#FA07C3\" " +
                "size=\"4\">中</font><font color=\"#3DBACC\" size=\"5\">字</font><font " +
                "color=\"#643382\" size=\"1\">体</font><font color=\"#80934E\" " +
                "size=\"6\">颜</font><font color=\"#3D4756\" size=\"1\">色</font><font " +
                "color=\"#28594B\" size=\"5\">，</font><font color=\"#2551B8\" " +
                "size=\"5\">缺</font><font color=\"#E36C9E\" size=\"7\">点</font><font " +
                "color=\"#CF61FB\" size=\"3\">是</font><font color=\"#6997AB\" " +
                "size=\"6\">改</font><font color=\"#E23A0F\" size=\"6\">不</font><font " +
                "color=\"#DC9506\" size=\"5\">了</font><font color=\"#8EDA6A\" " +
                "size=\"4\">字</font><font color=\"#53C058\" size=\"2\">体</font><font " +
                "color=\"#4C35F5\" size=\"5\">大</font><font color=\"#F60C83\" size=\"5\">小</font>" +
                "<font color=\"#EA227F\" size=\"3\">setText(Html.fromHtml());http://blog.csdn" +
                ".net/johnsonblog/article/details/7741972</font>"));

        SpannableString spannableString = new SpannableString
                ("使用SpannableString既解决了颜色问题，还搞定了大小问题。但原生的textview存在英文换行的问题\nhttp://www" +
                        ".jcodecraeer" +
                        ".com/a/anzhuokaifa/androidkaifa/2015/1009/3553.html");
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor
                ("#ff9900"));
        spannableString.setSpan(foregroundColorSpan, 0, spannableString.length(), Spanned
                .SPAN_EXCLUSIVE_EXCLUSIVE);
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(25);
        spannableString.setSpan(absoluteSizeSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView2.setText(spannableString);

        SpannableString spannableString1 = new SpannableString("爱拼购  5.3MB");
        ForegroundColorSpan foregroundColorSpan1 = new ForegroundColorSpan(Color.parseColor
                ("#EA227F"));
        spannableString1.setSpan(foregroundColorSpan1, 0, spannableString1.length(), Spanned
                .SPAN_EXCLUSIVE_EXCLUSIVE);
        AbsoluteSizeSpan absoluteSizeSpan1 = new AbsoluteSizeSpan(12);
        spannableString1.setSpan(absoluteSizeSpan1, 5, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView3.setText(spannableString1);

        SpannableString spannableString2 = new SpannableString
                ("这个自定义TextView解决了英文换行的问题\nhttp://www" +
                        ".jcodecraeer" +
                        ".com/a/anzhuokaifa/androidkaifa/2015/1009/3553.html");
        AbsoluteSizeSpan absoluteSizeSpan2 = new AbsoluteSizeSpan(25);
        spannableString.setSpan(absoluteSizeSpan2, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView4.setText("这个自定义TextView解决了英文换行的问题\nhttp://www" +
                ".jcodecraeer" +
                ".com/a/anzhuokaifa/androidkaifa/2015/1009/3553.html");
        textView4.invalidate();
    }
}
