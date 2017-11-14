package com.xiaokun.xiusou.demo6.Adapter;

import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.socks.library.KLog;
import com.xiaokun.xiusou.demo6.Bean.DownLoadListData;
import com.xiaokun.xiusou.demo6.R;
import com.xiaokun.xiusou.demo6.Utils.PicassoUtil;
import com.yuyh.library.utils.PackageUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/12/24 0024.
 */

public class TaskDownAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private onItemClickListener mItemClickListener;
    private List<DownLoadListData.DownLoadListDetail> mList;

    public interface onItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public TaskDownAdapter(Context context, List<DownLoadListData.DownLoadListDetail> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_task_down, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder myViewHolder = (MyViewHolder) holder;

        if (holder instanceof MyViewHolder) {
            myViewHolder.title.setText(mList.get(position).getApplyname() + mList.get(position)
                    .getFilesize());
            myViewHolder.downloads.setText("累计" + mList.get(position).getDownloads() + "人领取");
            myViewHolder.describle.setText(mList.get(position).getDescribe());
            PicassoUtil.loadImageViewHolder(mContext, mList.get(position).getIcon(), myViewHolder
                    .imageView);

            myViewHolder.btn_download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (PackageUtils.isInsatalled(mContext, "com.xiaokun.xiusou.demo66")) {
                        myViewHolder.btn_download.setText("已安装");
                        myViewHolder.btn_download.setClickable(false);
                        return;
                    }
                    myViewHolder.btn_download.setClickable(false);
                    OkHttpUtils.post().addParams("uid", "522414716").addParams("appkey",
                            "123456").addParams("baoming", "com.xiaokun.xiusou.demo66").url
                            ("http://z.01808.cn/api/download/").build().execute(new FileCallBack
                            (Environment.getExternalStorageDirectory().getAbsolutePath(),
                                    "XiusouDown.apk") {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                        }

                        @Override
                        public void onResponse(File response, int id) {
                            myViewHolder.btn_download.setText("已下载");
                            KLog.d("onResponse :" + response.getAbsolutePath());
                            PackageUtils.install(mContext, response);
//                            Intent i = new Intent();
//                            i.setAction(Intent.ACTION_VIEW);
//                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            i.setDataAndType(Uri.fromFile(new File(Environment
//                                            .getExternalStorageDirectory(), "UC.apk")),
//                                    "application/vnd.android.package-archive");
//                            mContext.startActivity(i);
                        }

                        @Override
                        public void inProgress(float progress, long total, int id) {
                            DecimalFormat fnum = new DecimalFormat("##0");
                            String p = fnum.format(progress * 100) + "%";
                            myViewHolder.btn_download.setText("下载中" + p);
                            KLog.d("progress:" + progress + "total:" + total + "id:" + id);
                        }
                    });
                }
            });
            myViewHolder.linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myViewHolder.download.getVisibility() == View.GONE) {
                        myViewHolder.download.setVisibility(View.VISIBLE);
                    } else {
                        myViewHolder.download.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linear;
        LinearLayout download;
        ImageView imageView;
        TextView title;
        TextView describle;
        TextView downloads;
        Button btn_download;

        public MyViewHolder(View itemView) {
            super(itemView);
            linear = (LinearLayout) itemView.findViewById(R.id.linear);
            download = (LinearLayout) itemView.findViewById(R.id.download);
            downloads = (TextView) itemView.findViewById(R.id.downloads);
            title = (TextView) itemView.findViewById(R.id.title);
            describle = (TextView) itemView.findViewById(R.id.describle);
            imageView = (ImageView) itemView.findViewById(R.id.list_imageview);
            btn_download = (Button) itemView.findViewById(R.id.btn_download);
        }
    }
}
