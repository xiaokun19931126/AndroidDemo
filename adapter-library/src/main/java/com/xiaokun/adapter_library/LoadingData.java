package com.xiaokun.adapter_library;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/15
 *     描述   : 加载item的实体类
 *     版本   : 1.0
 * </pre>
 */

public class LoadingData implements BaseAdapterData
{
    @Override
    public int getItemViewType()
    {
        return BaseRecyclerAdapter.LOAD_VIEW_ID;
    }
}
