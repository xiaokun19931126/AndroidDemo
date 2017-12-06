package com.xiaokun.http_library.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit的单例类 封装
 *
 * @author xiaokun
 * @date 2017/11/30
 */

public class RetrofitClient
{
    private static volatile RetrofitClient instance;
    private Retrofit.Builder retrofitBuilder;
    private OkHttpClient.Builder okhttpBuilder;

    private RetrofitClient()
    {
        okhttpBuilder = HttpClient.getInstance().getBuilder();

        retrofitBuilder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
    }

    public static RetrofitClient getInstance()
    {
        if (instance == null)
        {
            synchronized (RetrofitClient.class)
            {
                if (instance == null)
                {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }

    public Retrofit.Builder getRetrofitBuilder()
    {
        return retrofitBuilder;
    }

    public Retrofit getRetrofit()
    {
        return retrofitBuilder.client(okhttpBuilder.build()).build();
    }
}
