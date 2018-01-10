package com.xiaokun.adapter_library;

import java.util.Collection;
import java.util.Map;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/10
 *     描述   : 对象判空
 *     版本   : 1.0
 * </pre>
 */

public class Check
{
    public static boolean isEmpty(CharSequence str)
    {
        return isNull(str) || str.length() == 0;
    }

    public static boolean isEmpty(Object[] os)
    {
        return isNull(os) || os.length == 0;
    }

    public static boolean isEmpty(Number[] os)
    {
        return isNull(os) || os.length == 0;
    }

    public static boolean isEmpty(Collection<?> l)
    {
        return isNull(l) || l.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> m)
    {
        return isNull(m) || m.isEmpty();
    }

    public static boolean isNull(Object o)
    {
        return o == null;
    }
}
