package com.xiaokun.http_library.retrofit;

import okhttp3.OkHttpClient;

/**
 * OkHttp的单例类 封装
 *
 * @author xiaokun
 * @date 2017/11/30
 */

public class HttpClient
{
    private static volatile HttpClient instance;
    private OkHttpClient.Builder builder;

    private HttpClient()
    {
        builder = new OkHttpClient.Builder();
    }

    public static HttpClient getInstance()
    {
        if (instance == null)
        {
            synchronized (HttpClient.class)
            {
                if (instance == null)
                {
                    instance = new HttpClient();
                }
            }
        }
        return instance;
    }

    public OkHttpClient.Builder getBuilder()
    {
        return builder;
    }
}
