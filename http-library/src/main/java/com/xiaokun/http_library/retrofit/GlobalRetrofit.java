package com.xiaokun.http_library.retrofit;


import com.xiaokun.http_library.interfaces.DownloadApi;
import com.xiaokun.http_library.interfaces.UploadFileApi;
import com.xiaokun.http_library.transformer.Transformer;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * @author xiaokun
 * @date 2017/11/30
 */

public class GlobalRetrofit
{
    private static volatile GlobalRetrofit instance;

    private GlobalRetrofit()
    {

    }

    public static GlobalRetrofit getInstance()
    {
        if (instance == null)
        {
            synchronized (GlobalRetrofit.class)
            {
                if (instance == null)
                {
                    instance = new GlobalRetrofit();
                }
            }
        }
        return instance;
    }

    /**
     * 自定义OkhttpClient
     *
     * @param client
     * @return
     */
    public GlobalRetrofit setOkHttpClient(OkHttpClient client)
    {
        getGlobalRetrofitBuidler().client(client);
        return this;
    }

    /**
     * 设置baseUrl
     *
     * @param baseUrl
     * @return
     */
    public GlobalRetrofit setBaseUrl(String baseUrl)
    {
        getGlobalRetrofitBuidler().baseUrl(baseUrl);
        return this;
    }

    /**
     * 是否设置打印拦截器
     *
     * @param isLog
     * @return
     */
    public GlobalRetrofit setLog(boolean isLog)
    {
        if (isLog)
        {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            getGlobalOkhttpBuilder().addInterceptor(loggingInterceptor);
        }
        return this;
    }

    /**
     * 设置连接超时
     *
     * @param secound
     * @return
     */
    public GlobalRetrofit setConnectTimeOut(long secound)
    {
        getGlobalOkhttpBuilder().connectTimeout(secound, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 设置读取超时
     *
     * @param secound
     * @return
     */
    public GlobalRetrofit setReadTimeOut(long secound)
    {
        getGlobalOkhttpBuilder().readTimeout(secound, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 设置写入超时
     *
     * @param secound
     * @return
     */
    public GlobalRetrofit setWriteTimeOut(long secound)
    {
        getGlobalOkhttpBuilder().writeTimeout(secound, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 设置拦截器，比如设置验证token的拦截器，或者添加请求头header的拦截器，或者缓存的拦截器
     *
     * @param interceptor
     * @return
     */
    public GlobalRetrofit setInterceptor(Interceptor interceptor)
    {
        getGlobalOkhttpBuilder().addInterceptor(interceptor);
        return this;
    }

    /**
     * 全局的Retrofit
     *
     * @return
     */
    public static Retrofit getGlobalRetrofit()
    {
        return RetrofitClient.getInstance().getRetrofit();
    }

    /**
     * 全局的Retrofit Buidler
     *
     * @return
     */
    public static Retrofit.Builder getGlobalRetrofitBuidler()
    {
        return RetrofitClient.getInstance().getRetrofitBuilder();
    }

    /**
     * 全局的Okhttp builder
     *
     * @return
     */
    public static OkHttpClient.Builder getGlobalOkhttpBuilder()
    {
        return HttpClient.getInstance().getBuilder();
    }

    /**
     * 请求api service
     *
     * @param kClass
     * @param <K>
     * @return
     */
    public static <K> K createApiService(Class<K> kClass)
    {
        return getGlobalRetrofit().create(kClass);
    }

    /**
     * 下载
     *
     * @param fileUrl
     * @return
     */
    public static Observable<ResponseBody> downloadFile(String fileUrl)
    {
        return RetrofitClient.getInstance().getRetrofit()
                .create(DownloadApi.class)
                .downloadFile(fileUrl)
                .compose(Transformer.<ResponseBody>switchSchedulers());
    }

    /**
     * 上传
     *
     * @param uploadUrl
     * @param filePath
     * @return
     */
    public static Observable<ResponseBody> uploadImg(String uploadUrl, String filePath)
    {
        File file = new File(filePath);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("uploaded_file", file.getName(), requestFile);

        return RetrofitClient.getInstance().getRetrofit()
                .create(UploadFileApi.class)
                .uploadImg(uploadUrl, body)
                .compose(Transformer.<ResponseBody>switchSchedulers());
    }
}
