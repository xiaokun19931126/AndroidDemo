package com.xiaokun.xiusou.demo6.CustomView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.yuyh.library.utils.DimenUtils;

/**
 * Created by xiaokun on 2017/7/6.
 */

public class CircleRingView extends View
{

    private Paint mPaint;
    private float mWidth = 0f;
    private float mPadding = 0f;
    private float startAngle = 0f;
    private float sweepAngle = 100f;
    private ValueAnimator valueAnimator;

    public CircleRingView(Context context)
    {
        this(context, null);
    }

    public CircleRingView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CircleRingView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint()
    {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(DimenUtils.dpToPx(3));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // if measure width more than the view height
        if (getMeasuredWidth() > getHeight())
        {
            mWidth = getMeasuredHeight();
        } else
        {
            mWidth = getMeasuredWidth();
        }
        mPadding = DimenUtils.dpToPx(3);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        mPaint.setColor(Color.argb(100, 255, 255, 255));
        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2 - mPadding, mPaint);
        mPaint.setColor(Color.WHITE);
        RectF rectF = new RectF(mPadding, mPadding, mWidth - mPadding, mWidth - mPadding);
        canvas.drawArc(rectF, startAngle, sweepAngle, false, mPaint);
    }

    /**
     * 启动动画
     */
    public void startAnimation()
    {
        stopAnimation();
        startViewAnim(0f, 1f, 1000);
    }

    /**
     * 停止动画
     */
    public void stopAnimation()
    {
        if (valueAnimator != null)
        {
            clearAnimation();
            valueAnimator.setRepeatCount(1);
            valueAnimator.cancel();
            valueAnimator.end();
        }
    }

    /**
     * 开始动画
     *
     * @param startF
     * @param endF
     * @param time
     * @return
     */
    private ValueAnimator startViewAnim(float startF, float endF, long time)
    {
        valueAnimator = ValueAnimator.ofFloat(startF, endF);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(time);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                startAngle = 360 * animatedValue;
                invalidate();
            }
        });

        if (!valueAnimator.isRunning())
        {
            valueAnimator.start();
        }

        return valueAnimator;
    }
}
