package com.xiaokun.xiusou.aidl.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author xiaokun
 * @date 2017/12/7
 */

public class Download implements Parcelable
{
    private int progress;
    private int currentFileSize;
    private int totalFileSize;

    public Download()
    {

    }

    public int getProgress()
    {
        return progress;
    }

    public void setProgress(int progress)
    {
        this.progress = progress;
    }

    public int getCurrentFileSize()
    {
        return currentFileSize;
    }

    public void setCurrentFileSize(int currentFileSize)
    {
        this.currentFileSize = currentFileSize;
    }

    public int getTotalFileSize()
    {
        return totalFileSize;
    }

    public void setTotalFileSize(int totalFileSize)
    {
        this.totalFileSize = totalFileSize;
    }

    @Override
    public String toString()
    {
        return "Download{" +
                "progress=" + progress +
                ", currentFileSize=" + currentFileSize +
                ", totalFileSize=" + totalFileSize +
                '}';
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(this.progress);
        dest.writeInt(this.currentFileSize);
        dest.writeInt(this.totalFileSize);
    }

    protected Download(Parcel in)
    {
        this.progress = in.readInt();
        this.currentFileSize = in.readInt();
        this.totalFileSize = in.readInt();
    }

    public static final Parcelable.Creator<Download> CREATOR = new Parcelable.Creator<Download>()
    {
        @Override
        public Download createFromParcel(Parcel source)
        {
            return new Download(source);
        }

        @Override
        public Download[] newArray(int size)
        {
            return new Download[size];
        }
    };
}
