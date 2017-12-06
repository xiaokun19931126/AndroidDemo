package com.xiaokun.http_library.download;


import com.xiaokun.http_library.exception.ApiException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import okhttp3.ResponseBody;

/**
 * @author xiaokun
 * @date 2017/11/30
 */

public abstract class BaseDownloadObserver implements Observer<ResponseBody>
{

    @Override
    public void onError(@NonNull Throwable e)
    {
        String msg = ApiException.handlerException(e).getMsg();
        setError(msg);
    }

    private void setError(String msg)
    {
        doOnError(msg);
    }

    protected abstract void doOnError(String msg);

}
