package com.xiaokun.xiusou.aidl.Adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaokun.xiusou.aidl.Bean.GankCategoryEntity;
import com.xiaokun.xiusou.aidl.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaokun
 * @date 2017/12/7
 */

public class SearchViewAdapter extends BaseQuickAdapter<GankCategoryEntity.ResultsBean, BaseViewHolder> implements Filterable
{

    private ArrayList<GankCategoryEntity.ResultsBean> mFilteredList;

    public SearchViewAdapter(@LayoutRes int layoutResId, @Nullable List<GankCategoryEntity.ResultsBean> data)
    {
        super(layoutResId, data);
    }

    @Override
    public void setNewData(@Nullable List<GankCategoryEntity.ResultsBean> data)
    {
        super.setNewData(data);
        mFilteredList = (ArrayList<GankCategoryEntity.ResultsBean>) data;
    }

    @Override
    protected void convert(final BaseViewHolder helper, GankCategoryEntity.ResultsBean item)
    {
        int limit = 48;
        String string = item.getDesc();
        String text = string.length() > limit ? string.substring(0, limit) + "..." : string;
        TextView desc = helper.getView(R.id.category_desc);
        TextView author = helper.getView(R.id.category_author);
        TextView date = helper.getView(R.id.category_date);

        desc.setText(text);
        author.setText(item.getWho());
        date.setText(item.getPublishedAt().substring(0, 10));

        helper.setOnClickListener(R.id.home_cv, new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(mContext, "dianji", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public Filter getFilter()
    {

        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence)
            {

                String charString = charSequence.toString();

                if (charString.isEmpty())
                {
                    mData = mFilteredList;
                } else
                {
                    ArrayList<GankCategoryEntity.ResultsBean> filteredList = new ArrayList<>();

                    for (GankCategoryEntity.ResultsBean resultsBean : mFilteredList)
                    {
                        String desc = resultsBean.getDesc();
                        String publishedAt = resultsBean.getPublishedAt();
                        String who = resultsBean.getWho();
                        if (desc == null)
                        {
                            desc = "";
                        }
                        if (publishedAt == null)
                        {
                            publishedAt = "";
                        }
                        if (who == null)
                        {
                            who = "";
                        }
                        if (desc.contains(charString) || publishedAt.contains(charString) || who.contains(charString))
                        {
                            filteredList.add(resultsBean);
                        }
                    }

                    mData = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mData;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults)
            {
                mData = (ArrayList<GankCategoryEntity.ResultsBean>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
