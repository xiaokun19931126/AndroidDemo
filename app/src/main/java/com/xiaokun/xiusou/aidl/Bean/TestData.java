package com.xiaokun.xiusou.aidl.Bean;

import java.util.List;

/**
 * @author xiaokun
 * @date 2017/11/13
 */

public class TestData
{

    private List<DataBean> Data;

    public List<DataBean> getData()
    {
        return Data;
    }

    public void setData(List<DataBean> Data)
    {
        this.Data = Data;
    }

    public static class DataBean
    {
        /**
         * name : 依迅
         */

        private String name;

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }
    }
}
