package com.xiaokun.xiusou.aidl.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaokun.xiusou.aidl.Bean.NewsData;
import com.xiaokun.xiusou.aidl.R;
import com.xiaokun.xiusou.aidl.Utils.ACache;
import com.xiaokun.xiusou.aidl.Utils.CommonHolder;
import com.xiaokun.xiusou.aidl.Utils.ItemAnimHelper;

import java.util.List;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class HomeAdapter extends RecyclerView.Adapter<CommonHolder> {

    private static final String TAG = "HomeAdapter1";
    public Context context;
    public List<NewsData.NewsDetails> list;
    public ItemAnimHelper itemAnimHelper = new ItemAnimHelper();
    ACache mCache;

    public interface onItemClickListener{
        void onItemClick(View view,int position);
    }

    private onItemClickListener mItemClickListener;

    public void setOnItemClickListener(onItemClickListener listener){
        this.mItemClickListener = listener;
    }

    public HomeAdapter(Context context, List<NewsData.NewsDetails> list) {
        this.context = context;
        this.list = list;
        mCache = ACache.get(context);
    }

    public HomeAdapter(Context context) {
        this.context = context;
        mCache = ACache.get(context);
    }

    @Override
    public CommonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonHolder.getHolder(context,parent,R.layout.item_home);
//        View view = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false);
//        MyViewHolder myViewHolder = new MyViewHolder(view);
//        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonHolder holder, final int position) {

        Log.d(TAG, TAG + ":" + position);
//        holder.tv.setText(list.get(position).getContent());
//
//        Picasso.with(context).load(list.get(position).getImg()).fit().into(holder.mImage);
//        Picasso.with(context).setIndicatorsEnabled(true);

        if (mItemClickListener!=null){
//            holder.cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int pos = holder.getLayoutPosition();
//                    mItemClickListener.onItemClick(v,pos);
//                }
//            });
        }
//        itemAnimHelper.showItemAnim(holder.cardView, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        CardView cardView;
        ImageView mImage;

        public MyViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            tv = (TextView) view.findViewById(R.id.id_num);
            mImage = (ImageView) view.findViewById(R.id.imageview);
        }
    }
}
