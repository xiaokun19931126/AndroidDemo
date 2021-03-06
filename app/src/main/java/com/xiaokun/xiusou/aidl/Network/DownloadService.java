package com.xiaokun.xiusou.aidl.Network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

/**
 * @author xiaokun
 * @date 2017/12/7
 */

public interface DownloadService
{
    //http://imtt.dd.qq.com/16891/34B853747BF8CAEBD21019FCDDAEC98C.apk?fsname=com.qq.reader_6.5.7.888_106.apk&csr=1bbd
    //http://dl-cdn.coolapkmarket.com/down/apk_upload/2017/0827/ys_1.0.8-release-for-139330-o_1bogs1gchvoekjv177f16id12ae10-uid-703101.apk?_upt=bfa30a5a1512619762
    //http://14.204.74.141/imtt.dd.qq.com/16891/88BF95CADA9CFDDC7E698F6B6744FCAD.apk?mkey=58aec27075da6ad8&f=6606&c=0&fsname=com.xiaocai.httpdemo_1.0_1.apk&csr=4d5s&p=.apk
    String baseUrl = "http://imtt.dd.qq.com/";

    /**
     * For downloading we use GET method. For downloading large files we need to add @Streaming
     * annotation to Retrofit Interface so that it does not load the complete file into memory.
     *
     * @return
     */
    @GET("16891/88BF95CADA9CFDDC7E698F6B6744FCAD.apk?mkey=58aec27075da6ad8&f=6606&c=0&fsname=com.xiaocai.httpdemo_1.0_1.apk&csr=4d5s&p=.apk")
    @Streaming
    Call<ResponseBody> downloadFile();
}
