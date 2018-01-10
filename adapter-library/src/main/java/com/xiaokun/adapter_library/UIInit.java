package com.xiaokun.adapter_library;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/10
 *     描述   : UI初始化接口
 *     版本   : 1.0
 * </pre>
 */

public interface UIInit
{
    int getContentViewId();

    void initViews();

    void initBeforeView();
}
