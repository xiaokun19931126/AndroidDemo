package com.xiaokun.xiusou.demo6.Bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/24 0024.
 */

public class CollectionData {

    List<CollectionDetail> list;
    String state;//1 查询成功  2	查询失败   3	登录失败

    public List<CollectionDetail> getList() {
        return list;
    }

    public String getState() {
        return state;
    }

    public void setList(List<CollectionDetail> list) {
        this.list = list;
    }

    public void setState(String state) {
        this.state = state;
    }

    public class CollectionDetail {
        String id;// 文章id
        String title;// 文章标题
        String img;// 标题图
        String jianjie; // 文章简介
        String addtime; // 收藏时间

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getJianjie() {
            return jianjie;
        }

        public void setJianjie(String jianjie) {
            this.jianjie = jianjie;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }
    }
}
