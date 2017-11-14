package com.xiaokun.xiusou.demo6.Utils;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
public class TextDrawable extends Drawable {
    private int mPaddingBottom;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private String mText = "";
    private TextPaint mTextPaint = new TextPaint();
    private PointF mTextPosition = new PointF();

    public TextDrawable(String paramString)
    {
        this.mText = paramString;
        this.mTextPaint.setAntiAlias(true);
        calculateTextPosition();
    }

    private void calculateTextPosition()
    {
        Rect localRect = getBounds();
        localRect = new Rect(localRect.left + this.mPaddingLeft, localRect.top + this.mPaddingTop, localRect.right - this.mPaddingRight, localRect.bottom - this.mPaddingBottom);
        float f = this.mTextPaint.measureText(this.mText);
        this.mTextPosition.set((localRect.width() - f) / 2.0F, localRect.bottom - this.mTextPaint.descent());
    }

    public void draw(Canvas paramCanvas)
    {
        paramCanvas.drawText(this.mText, this.mTextPosition.x, this.mTextPosition.y, this.mTextPaint);
    }

    public int getOpacity()
    {
        return PixelFormat.TRANSLUCENT;
    }

    public void setAlpha(int paramInt)
    {
        this.mTextPaint.setAlpha(paramInt);
    }

    public void setBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
        super.setBounds(paramInt1, paramInt2, paramInt3, paramInt4);
        calculateTextPosition();
    }

    public void setColorFilter(ColorFilter paramColorFilter)
    {
        this.mTextPaint.setColorFilter(paramColorFilter);
    }

    public void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
        this.mPaddingLeft = paramInt1;
        this.mPaddingTop = paramInt2;
        this.mPaddingRight = paramInt3;
        this.mPaddingBottom = paramInt4;
        calculateTextPosition();
    }

    public void setTextColor(int paramInt)
    {
        this.mTextPaint.setColor(paramInt);
    }

    public void setTextSize(float paramFloat)
    {
        this.mTextPaint.setTextSize(paramFloat);
        calculateTextPosition();
    }
}
