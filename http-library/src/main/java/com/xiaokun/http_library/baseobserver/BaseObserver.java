package com.xiaokun.http_library.baseobserver;


import com.xiaokun.http_library.exception.ApiException;
import com.xiaokun.http_library.interfaces.ISubscriber;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author xiaokun
 * @date 2017/11/30
 */

public abstract class BaseObserver<T> implements Observer<T>, ISubscriber<T>
{
    @Override
    public void onSubscribe(@NonNull Disposable d)
    {
        doOnSubscribe(d);
    }

    @Override
    public void onNext(@NonNull T t)
    {
        doOnNext(t);
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
