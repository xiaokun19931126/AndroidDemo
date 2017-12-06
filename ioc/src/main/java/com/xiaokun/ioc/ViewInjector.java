package com.xiaokun.ioc;

/**
 * @author xiaokun
 * @date 2017/12/6
 */

public interface ViewInjector<T>
{
    void inject(T t, Object source);
}
