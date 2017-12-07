package com.xiaokun.xiusou.demo6.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.xiaokun.http_library.RxHttpUtils;
import com.xiaokun.http_library.observer.CommonObserver;
import com.xiaokun.http_library.transformer.Transformer;
import com.xiaokun.xiusou.demo6.Adapter.SearchViewAdapter;
import com.xiaokun.xiusou.demo6.Bean.GankCategoryEntity;
import com.xiaokun.xiusou.demo6.CustomView.OffsetDecoration;
import com.xiaokun.xiusou.demo6.Network.GankService;
import com.xiaokun.xiusou.demo6.R;
import com.yuyh.library.Base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author xiaokun
 * @date 2017/12/7
 */

public class SearchViewWithRvActivity extends BaseActivity
{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.card_recycler_view)
    RecyclerView recyclerView;
    private List<GankCategoryEntity.ResultsBean> datas;
    private SearchViewAdapter adapter;


    @Override
    public void initData(Bundle bundle)
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_search_view_with_rv;
    }

    @Override
    protected void initPresenter()
    {

    }

    @Override
    public void initView()
    {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        initRecyclerView();
    }

    private void initRecyclerView()
    {
        datas = new ArrayList<>();
        adapter = new SearchViewAdapter(R.layout.item_search_view, datas);
        final int spacing = getResources().getDimensionPixelSize(R.dimen.dimen_2_dp);
        recyclerView.addItemDecoration(new OffsetDecoration(spacing));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void doBusiness(Context context)
    {
        RxHttpUtils.createApi(GankService.class).getCategoryData("Android", 20, 1)
                .compose(Transformer.<GankCategoryEntity>switchSchedulers())
                .subscribe(new CommonObserver<GankCategoryEntity>()
                {
                    @Override
                    protected void onError(String errorMsg)
                    {

                    }

                    @Override
                    protected void onSuccess(GankCategoryEntity gankCategoryEntity)
                    {
                        if (gankCategoryEntity == null || gankCategoryEntity.getResults() == null)
                        {
                            return;
                        }
                        datas = gankCategoryEntity.getResults();
                        adapter.setNewData(datas);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    private void search(SearchView searchView)
    {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
