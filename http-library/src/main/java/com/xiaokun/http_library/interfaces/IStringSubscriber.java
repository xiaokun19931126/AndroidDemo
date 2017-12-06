package com.xiaokun.http_library.interfaces;

import io.reactivex.disposables.Disposable;

/**
 * @author xiaokun
 * @date 2017/11/30
 */

public interface IStringSubscriber
{
    /**
     * OnSubscribe 回调
     *
     * @param disposable
     */
    void doOnSubscribe(Disposable disposable);

    /**
     * onNext 回调
     *
     * @param response
     */
    void doOnNext(String response);

    /**
     * onError 回调
     *
     * @param errorMsg
     */
    void doOnError(String errorMsg);

    /**
     * onCompleted 回调
     */
    void doOnCompleted();
}
