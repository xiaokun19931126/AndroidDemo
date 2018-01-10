package com.xiaokun.adapter_library;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/10
 *     描述   : 实体类必须实现接口
 *     版本   : 1.0
 * </pre>
 */

public interface BaseAdapterData
{
    /**
     * 返回recyclerView类型，其实就是layout对应的id
     *
     * @return
     */
    int getItemViewType();
}
