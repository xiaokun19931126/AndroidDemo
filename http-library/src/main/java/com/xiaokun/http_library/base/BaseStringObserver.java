package com.xiaokun.http_library.base;


import com.xiaokun.http_library.exception.ApiException;
import com.xiaokun.http_library.interfaces.IStringSubscriber;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author xiaokun
 * @date 2017/11/30
 */

public abstract class BaseStringObserver implements Observer<String>, IStringSubscriber
{
    @Override
    public void onSubscribe(@NonNull Disposable d)
    {
        doOnSubscribe(d);
    }

    @Override
    public void onNext(@NonNull String s)
    {
        doOnNext(s);
    }

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

    @Override
    public void onComplete()
    {
        doOnCompleted();
    }
}
