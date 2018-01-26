package com.xiaokun.xiusou.aidl.Network;

import com.xiaokun.xiusou.aidl.Bean.Response;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @author xiaokun
 * @date 2017/12/7
 */

public interface UploadService
{
    /**
     * We have defined a Retrofit interface with Multipart as the type to upload the image.
     *
     * @param image
     * @return
     */
    @Multipart
    @POST("/images/upload")
    Call<Response> uploadImage(@Part MultipartBody.Part image);
}
