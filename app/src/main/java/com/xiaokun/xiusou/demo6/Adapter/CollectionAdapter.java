package com.xiaokun.xiusou.demo6.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.socks.library.KLog;
import com.xiaokun.xiusou.demo6.Bean.CollectionData;
import com.xiaokun.xiusou.demo6.R;
import com.xiaokun.xiusou.demo6.Utils.PicassoUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/12/24 0024.
 */

public class CollectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_NO_DATA = 2;
    public List<CollectionData.CollectionDetail> mList;
    private Context mContext;
    private int mPage;
    private onItemClickListener mItemClickListener;

    public interface onItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public CollectionAdapter(Context context, List<CollectionData.CollectionDetail> list, int
            page) {
        mContext = context;
        mList = list;
        mPage = page;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            KLog.d("position:" + position);
            if ((mPage * 10) > mList.size()) {
                return TYPE_NO_DATA;
            } else {
                return TYPE_FOOTER;
            }
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_collection, parent,
                    false);

            return new MyViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            KLog.d(viewType);
            View view;
            view = LayoutInflater.from(mContext).inflate(R.layout.item_foot, parent,
                    false);
//            if (mList.size() == (mPage * 10)) {
//                view = LayoutInflater.from(mContext).inflate(R.layout.item_foot, parent,
//                        false);
//            } else {
//                view = LayoutInflater.from(mContext).inflate(R.layout.item_foot1, parent,
//                        false);
//            }
            return new FootViewHolder(view);
        } else if (viewType == TYPE_NO_DATA) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_foot1, parent,
                    false);
            return new FootViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            final MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.title.setText(mList.get(position).getTitle());
            myViewHolder.jianjie.setText(mList.get(position).getJianjie());
            myViewHolder.addTime.setText(mList.get(position).getAddtime());
            PicassoUtil.loadImageViewHolder(mContext, mList.get(position).getImg(), myViewHolder
                    .imageView);
            if (mItemClickListener != null) {
                myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        int pos = myViewHolder.getLayoutPosition();
                        mItemClickListener.onItemClick(myViewHolder.itemView, pos);
                    }
                });
            }
        } else if (holder instanceof FootViewHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;
        private TextView jianjie;
        private TextView addTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageview);
            title = (TextView) itemView.findViewById(R.id.title);
            jianjie = (TextView) itemView.findViewById(R.id.jianjie);
            addTime = (TextView) itemView.findViewById(R.id.addtime);
        }
    }

    class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }
}
