package com.xiaokun.xiusou.aidl.Bean;

/**
 * @author xiaokun
 * @date 2017/12/7
 */

public class Response
{
    private String message;
    private String path;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    @Override
    public String toString()
    {
        return "Response{" +
                "message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
