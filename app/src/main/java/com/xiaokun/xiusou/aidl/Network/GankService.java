package com.xiaokun.xiusou.aidl.Network;

import com.xiaokun.xiusou.aidl.Bean.GankCategoryEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author xiaokun
 * @date 2017/12/7
 */

public interface GankService
{
    @GET("data/{category}/{count}/{page}")
    Observable<GankCategoryEntity> getCategoryData(@Path("category") String category,
                                                   @Path("count") int count, @Path("page") int page);
}
