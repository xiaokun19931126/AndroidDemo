package com.xiaokun.http_library.entity;

/**
 * @author xiaokun
 * @date 2017/11/29
 */

public class BaseData<T>
{
    /**
     * 状态码
     */
    private int code;
    /**
     * 描述
     */
    private String msg;
    /**
     * 实体类
     */
    private T data;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "BaseData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
