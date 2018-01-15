package com.xiaokun.adapter_library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import static com.xiaokun.adapter_library.LoadingViewHolder.STATUS_END;
import static com.xiaokun.adapter_library.LoadingViewHolder.STATUS_FAIL;
import static com.xiaokun.adapter_library.LoadingViewHolder.STATUS_LOADING;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/10
 *     描述   : adapter基类,通过registerHolder方法注入ViewHolder类
 *              使用方式：
 *              BaseRecyclerAdapter adapter = new BaseRecyclerAdapter(Context);
 *              adapter.registerHolder(BaseViewHoler继承类.class, new BaseAdapterData继承类());
 *              recyclerView.setAdapter(adapter);
 *     版本   : 1.0
 * </pre>
 */

public class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder>
{
    private SparseArray<Class<? extends BaseViewHolder>> typeHolders = new SparseArray();
    private List<BaseAdapterData> mData = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflater;
    private OnLoadMoreListener onLoadMoreListener;
    private RecyclerView mRecyclerView;
    private LoadingViewHolder loadingViewHolder;

    public interface OnLoadMoreListener
    {
        void onLoadMore();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener, RecyclerView recyclerView)
    {
        typeHolders.put(LOAD_VIEW_ID, LoadingViewHolder.class);
        this.onLoadMoreListener = listener;
        if (getRecyclerView() == null)
        {
            setRecyclerView(recyclerView);
        }
    }

    public BaseRecyclerAdapter(Context context)
    {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        addLoadMoreData();
    }


    public RecyclerView getRecyclerView()
    {
        return mRecyclerView;
    }

    private void setRecyclerView(RecyclerView recyclerView)
    {
        this.mRecyclerView = recyclerView;
    }

    public SparseArray<Class<? extends BaseViewHolder>> getTypeHolders()
    {
        return typeHolders;
    }

    public void registerHolder(Class<? extends BaseViewHolder> viewHolder, int itemViewType)
    {
        typeHolders.put(itemViewType, viewHolder);
    }

    public <T extends BaseAdapterData> void registerHolder(Class<? extends BaseViewHolder> viewHolder, T data)
    {
        if (data == null)
        {
            return;
        }
        typeHolders.put(data.getItemViewType(), viewHolder);
        addData(data);
    }

    public void registerHolder(Class<? extends BaseViewHolder> viewHolder, List<? extends BaseAdapterData> data)
    {
        if (Check.isEmpty(data))
        {
            return;
        }
        typeHolders.put(data.get(0).getItemViewType(), viewHolder);
        addData(data);
    }

    public void setData(List<? extends BaseAdapterData> data)
    {
        if (Check.isEmpty(data))
        {
            return;
        }
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void clear()
    {
        mData.clear();
    }

    public void addData(List<? extends BaseAdapterData> data)
    {
        if (data == null)
        {
            return;
        }
        mData.addAll(mData.size() - 1, data);
        notifyItemRangeInserted(mData.size() - data.size(), data.size());
    }

    public <T extends BaseAdapterData> void addData(T data)
    {
        if (data == null)
        {
            return;
        }
        mData.add(mData.size() - 1, data);
        notifyItemRangeInserted(mData.size() - 1, 1);
    }

    private boolean onBind;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = mInflater.inflate(viewType, parent, false);
        BaseViewHolder viewHolder = new NoDataViewHolder(itemView, this);
        try
        {
            Class<?> cls = typeHolders.get(viewType);
            Constructor holderConstructor = cls.getDeclaredConstructor(View.class, BaseRecyclerAdapter.class);
            holderConstructor.setAccessible(true);
            viewHolder = (BaseViewHolder) holderConstructor.newInstance(itemView, this);
        } catch (NoSuchMethodException e)
        {
            Log.e("BaseRecyclerAdapter", typeHolders.get(viewType) + "无法创建内部viewholder");
        } catch (Exception e)
        {
            Log.e("BaseRecyclerAdapter", "e:" + e.getMessage());
        }
        return viewHolder;
    }

    private void addLoadMoreData()
    {
        mData.add(new LoadingData());
        LoadingViewHolder.state = STATUS_LOADING;
    }

    public List<BaseAdapterData> getData()
    {
        return mData;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position)
    {
        onBind = true;
        if (Check.isEmpty(mData) || Check.isNull(mData.get(position)))
        {
            return;
        }
        if (getItemViewType(position) != holder.getContentViewId())
        {
            return;
        }

        if (holder instanceof LoadingViewHolder)
        {
            this.loadingViewHolder = (LoadingViewHolder) holder;
            switch (LoadingViewHolder.state)
            {
                case STATUS_LOADING:
                    //保证第一次不执行这个
                    if (position > 0)
                    {
                        onLoadMoreListener.onLoadMore();
                    }
                    break;
                case STATUS_END:
                    loadingViewHolder.loadingEnd();
                    break;
                case STATUS_FAIL:
                    loadingViewHolder.loadingFailed();
                    break;
                default:

                    break;
            }
        }
        holder.updateItem(mData.get(position), position);
        onBind = false;
    }

    @Override
    public int getItemCount()
    {
        return mData.size();
    }

    public static final int LOAD_VIEW_ID = R.layout.loading_view_adapter;

    @Override
    public int getItemViewType(int position)
    {
        return mData.get(position).getItemViewType();
    }

    public void loadFailed()
    {
        if (loadingViewHolder != null)
        {
            loadingViewHolder.loadingFailed();
            if (!onBind)
            {
                notifyItemChanged(mData.size());
            }
        }
    }

    public void loadEnd()
    {
        if (loadingViewHolder != null)
        {
            loadingViewHolder.loadingEnd();
            if (!onBind)
            {
                notifyItemChanged(mData.size());
            }
        }
    }
}
