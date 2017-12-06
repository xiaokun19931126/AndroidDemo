package com.xiaokun.http_library;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.xiaokun.http_library.retrofit.GlobalRetrofit;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * ..借鉴的https://github.com/lygttpod/RxHttpUtils.git。。。跟着手写了一遍
 *
 * @author xiaokun
 * @date 2017/11/30
 */

public class RxHttpUtils
{
    @SuppressLint("StaticFieldLeak")
    private static RxHttpUtils instance;
    @SuppressLint("StaticFieldLeak")
    private static Application context;

    private static List<Disposable> disposables;

    private static String networkData;

    public static RxHttpUtils getInstance()
    {
        checkInitialize();
        if (instance == null)
        {
            synchronized (RxHttpUtils.class)
            {
                if (instance == null)
                {
                    instance = new RxHttpUtils();
                    disposables = new ArrayList<>();
                }
            }
        }
        return instance;
    }


    /**
     * 必须在全局Application先调用，获取context上下文，否则缓存无法使用
     *
     * @param app Application
     */
    public static void init(Application app)
    {
        context = app;
    }

    /**
     * 获取全局上下文
     */
    public static Context getContext()
    {
        checkInitialize();
        return context;
    }

    /**
     * 检测是否调用初始化方法
     */
    private static void checkInitialize()
    {
        if (context == null)
        {
            throw new ExceptionInInitializerError("请先在全局Application中调用 RxHttpUtils.init() 初始化！");
        }
    }

    public GlobalRetrofit config()
    {
        return GlobalRetrofit.getInstance();
    }

    /**
     * 使用全局参数创建请求
     *
     * @param cls Class
     * @param <K> K
     * @return 返回
     */
    public static <K> K createApi(Class<K> cls)
    {
        return GlobalRetrofit.createApiService(cls);
    }

//    /**
//     * 获取单个请求配置实例
//     *
//     * @return SingleRxHttp
//     */
//    public static SingleRxHttp getSInstance()
//    {
//        return SingleRxHttp.getInstance();
//    }

    /**
     * 下载文件
     *
     * @param fileUrl
     * @return
     */
    public static Observable<ResponseBody> downloadFile(String fileUrl)
    {
        return GlobalRetrofit.downloadFile(fileUrl);
    }

    /**
     * 上传文件
     *
     * @param uploadUrl 地址
     * @param filePath  文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadFile(String uploadUrl, String filePath)
    {
        return GlobalRetrofit.uploadImg(uploadUrl, filePath);

    }

//    /**
//     * 获取Cookie
//     *
//     * @return HashSet
//     */
//    public static HashSet<String> getCookie()
//    {
//        HashSet<String> preferences = (HashSet<String>) SPUtils.get(SPKeys.COOKIE, new HashSet<String>());
//        return preferences;
//    }

    /**
     * 获取disposable 在onDestroy方法中取消订阅disposable.dispose()
     *
     * @param disposable disposable
     */
    public static void addDisposable(Disposable disposable)
    {
        if (disposables != null)
        {
            disposables.add(disposable);
        }
    }

    /**
     * 取消所有请求
     */
    public static void cancelAllRequest()
    {
        if (disposables != null)
        {
            for (Disposable disposable : disposables)
            {
                disposable.dispose();
            }
            disposables.clear();
        }
    }

    /**
     * 取消单个请求
     */
    public static void cancelSingleRequest(Disposable disposable)
    {
        if (disposable != null && !disposable.isDisposed())
        {
            disposable.dispose();
        }
    }
}
