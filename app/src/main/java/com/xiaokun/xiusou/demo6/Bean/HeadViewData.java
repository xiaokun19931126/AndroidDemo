package com.xiaokun.xiusou.demo6.Bean;

import com.xiaokun.adapter_library.BaseAdapterData;
import com.xiaokun.xiusou.demo6.R;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/15
 *     描述   :
 *     版本   : 1.0
 * </pre>
 */

public class HeadViewData implements BaseAdapterData
{
    @Override
    public int getItemViewType()
    {
        return R.layout.headview_girl_item;
    }
}
