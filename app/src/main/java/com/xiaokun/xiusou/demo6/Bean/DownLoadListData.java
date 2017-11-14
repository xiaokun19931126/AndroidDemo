package com.xiaokun.xiusou.demo6.Bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/26 0026.
 */

public class DownLoadListData {
    List<DownLoadListDetail> list;

    public List<DownLoadListDetail> getList() {
        return list;
    }

    public void setList(List<DownLoadListDetail> list) {
        this.list = list;
    }

    public class DownLoadListDetail {
        String id;
        String appid;
        String icon;
        String applyname;
        String baoming;
        String describe;
        String price;
        String filesize;
        String downloads;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getApplyname() {
            return applyname;
        }

        public void setApplyname(String applyname) {
            this.applyname = applyname;
        }

        public String getBaoming() {
            return baoming;
        }

        public void setBaoming(String baoming) {
            this.baoming = baoming;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getFilesize() {
            return filesize;
        }

        public void setFilesize(String filesize) {
            this.filesize = filesize;
        }

        public String getDownloads() {
            return downloads;
        }

        public void setDownloads(String downloads) {
            this.downloads = downloads;
        }
    }
}
