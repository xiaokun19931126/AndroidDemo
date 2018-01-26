package com.xiaokun.xiusou.aidl.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.xiaokun.adapter_library.BaseAdapterData;
import com.xiaokun.adapter_library.BaseRecyclerAdapter;
import com.xiaokun.adapter_library.LoadingData;
import com.xiaokun.http_library.RxHttpUtils;
import com.xiaokun.http_library.observer.CommonObserver;
import com.xiaokun.http_library.transformer.Transformer;
import com.xiaokun.xiusou.aidl.Bean.GankCategoryEntity;
import com.xiaokun.xiusou.aidl.Bean.HeadViewData;
import com.xiaokun.xiusou.aidl.CustomView.OffsetDecoration;
import com.xiaokun.xiusou.aidl.Network.GankService;
import com.xiaokun.xiusou.aidl.R;
import com.xiaokun.xiusou.aidl.holder.SearchHeadHolder;
import com.xiaokun.xiusou.aidl.holder.SearchViewHolder;
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
    //    private SearchViewAdapter adapter;
    private BaseRecyclerAdapter adapter;


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
//        adapter = new SearchViewAdapter(R.layout.item_search_view, datas);
        adapter = new BaseRecyclerAdapter(this);
        final int spacing = getResources().getDimensionPixelSize(R.dimen.dimen_2_dp);
        recyclerView.addItemDecoration(new OffsetDecoration(spacing));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
        {
            @Override
            public int getSpanSize(int position)
            {
                BaseAdapterData data = adapter.getData().get(position);
                return data instanceof LoadingData || data instanceof HeadViewData ? gridLayoutManager.getSpanCount() : 1;
            }
        });

        adapter.setOnLoadMoreListener(new BaseRecyclerAdapter.OnLoadMoreListener()
        {
            @Override
            public void onLoadMore()
            {
                page++;
                Log.e("SearchViewWith", "onLoadMore(SearchViewWithRvActivity.java:109)" + page);
                if (page > 3)
                {
                    adapter.loadEnd();
                } else
                {
                    loadData(page);
                }
            }
        }, recyclerView);
        recyclerView.setAdapter(adapter);
    }

    private int page = 1;

    @Override
    public void doBusiness(Context context)
    {
        loadData(page);
//        final String url = "https://webapi.bss.comlbs.com/api/AppVehicleData/GetUserPlatAll";
//
//        new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                try
//                {
//                    URL mUrl = new URL(url);
//                    HttpURLConnection conn = (HttpURLConnection) mUrl.openConnection();
//                    conn.setRequestMethod("GET");
//                    conn.setRequestProperty("Accept-Encoding", "gzip");
//                    conn.setRequestProperty("token", "846AC1882D734268BCA62636FB18B9A3");
//
//                    int responseCode = conn.getResponseCode();
//                    if (responseCode == HttpURLConnection.HTTP_OK)
//                    {
//                        String responseMessage = conn.getResponseMessage();
//                        String contentEncoding = conn.getContentEncoding();
//                        String encoding = conn.getHeaderField("Content-Encoding");
//                        boolean gzipped = encoding != null && encoding.toLowerCase().contains("gzip");
//                        InputStream in = conn.getInputStream();
//                        if (!gzipped)
//                        {
//                            //un gzip
//                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                            int len1;
//                            byte[] buffer1 = new byte[1024];
//                            while ((len1 = in.read(buffer1)) != -1)
//                            {
//                                byteArrayOutputStream.write(buffer1, 0, len1);
//                            }
//                            in.close();
//                            byteArrayOutputStream.close();
//                            final String str1 = new String(byteArrayOutputStream.toByteArray(), "utf-8");
//                        } else
//                        {
//                            //do gzip
//                            in = new GZIPInputStream(conn.getInputStream());
//                            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
//                            int len;
//                            byte[] buffer = new byte[1024];
//                            while ((len = in.read(buffer)) != -1)
//                            {
//                                arrayOutputStream.write(buffer, 0, len);
//                            }
//                            in.close();
//                            arrayOutputStream.close();
//                            final String str = new String(arrayOutputStream.toByteArray(), "utf-8");
//                        }
//                    }
//                } catch (MalformedURLException e)
//                {
//                    e.printStackTrace();
//                } catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }).start();


    }

    private void loadData(final int page)
    {
        RxHttpUtils.createApi(GankService.class).getCategoryData("Android", 20, page)
                .compose(Transformer.<GankCategoryEntity>switchSchedulers())
                .subscribe(new CommonObserver<GankCategoryEntity>()
                {
                    @Override
                    protected void onError(String errorMsg)
                    {
                        adapter.loadFailed();
                    }

                    @Override
                    protected void onSuccess(GankCategoryEntity gankCategoryEntity)
                    {
                        if (gankCategoryEntity == null || gankCategoryEntity.getResults() == null)
                        {
                            return;
                        }

                        datas = gankCategoryEntity.getResults();
                        if (page == 1)
                        {
                            adapter.registerHolder(SearchHeadHolder.class, new HeadViewData());
                        }
                        adapter.registerHolder(SearchViewHolder.class, datas);
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
//                adapter.getFilter().filter(newText);
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
