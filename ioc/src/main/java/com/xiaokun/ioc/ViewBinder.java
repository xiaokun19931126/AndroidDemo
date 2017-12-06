package com.xiaokun.ioc;

import android.app.Activity;

/**
 * @author xiaokun
 * @date 2017/12/6
 */

public class ViewBinder
{
    private static final String SUFFIX = "$$ViewInjector";

    //Activity 中调用的方法
    public static void bind(Activity activity)
    {
        bind(activity, activity);
    }

    /**
     * 1.寻找对应的代理类
     * 2.调用接口提供的绑定方法
     *
     * @param host
     * @param root
     */
    private static void bind(final Object host, final Object root)
    {
        if (host == null || root == null)
        {
            return;
        }

        Class<?> aClass = host.getClass();
        String proxyClassFullName = aClass.getName() + SUFFIX;    //拼接生成类的名称

        try
        {
            Class<?> proxyClass = Class.forName(proxyClassFullName);
            ViewInjector viewInjector = (ViewInjector) proxyClass.newInstance();
            if (viewInjector != null)
            {
                viewInjector.inject(host, root);
            }
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (InstantiationException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

    }
}
