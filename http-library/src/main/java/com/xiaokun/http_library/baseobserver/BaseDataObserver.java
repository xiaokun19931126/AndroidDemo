package com.xiaokun.http_library.baseobserver;


import com.xiaokun.http_library.entity.BaseData;
import com.xiaokun.http_library.exception.ApiException;
import com.xiaokun.http_library.interfaces.IDataSubscriber;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author xiaokun
 * @date 2017/11/29
 */

public abstract class BaseDataObserver<T> implements Observer<BaseData<T>>, IDataSubscriber<T>
{
    @Override
    public void onSubscribe(@NonNull Disposable d)
    {
        doOnSubscribe(d);
    }

    @Override
    public void onNext(@NonNull BaseData<T> baseData)
    {
        doOnNext(baseData);
    }

    @Override
    public void onError(@NonNull Throwable e)
    {
        String msg = ApiException.handlerException(e).getMsg();
        setError(msg);
    }

    @Override
    public void onComplete()
    {
        doOnCompleted();
    }

    private void setError(String errorMsg)
    {
        doOnError(errorMsg);
    }

}
