package com.xiaokun.xiusou.aidl.Activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.xiaokun.xiusou.aidl.Bean.Response;
import com.xiaokun.xiusou.aidl.Network.UploadService;
import com.xiaokun.xiusou.aidl.R;
import com.yuyh.library.Base.BaseActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Android Upload Image using Retrofit
 * <p>
 * <p>
 * In our MainActivity when the image select button is pressed we start a Intent to pick image.
 * On Result, we will get the Uri of the image. From that Uri,
 * we will open an InputStream and read the image contents as a byte array.
 * <p><b>From Android Nougat there were some changes which do not allow us to get the Real file path of the image.</b><p/>
 * So we are using this alternate method. With the byte array, we will create an okhttp RequestBody object.
 * From that, we can create a MultipartBody.Part object which can be used with the Retrofit interface to
 * upload the image. We display the message in a Snackbar when the image is uploaded, or any error message is returned.
 *
 * @author xiaokun
 * @date 2017/12/7
 */

public class RetrofitUploadActivity extends BaseActivity
{
    public static final String TAG = RetrofitUploadActivity.class.getSimpleName();

    private static final int INTENT_REQUEST_CODE = 100;

    public static final String URL = "http://192.168.1.196:8080";
    private String mImageUrl = "";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.btn_select_image)
    Button mBtImageSelect;
    @Bind(R.id.btn_show_image)
    Button mBtImageShow;
    @Bind(R.id.progress)
    ProgressBar mProgressBar;

    @Override
    public void initData(Bundle bundle)
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_retrofit_upload;
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
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void doBusiness(Context context)
    {

    }

    @OnClick({R.id.btn_select_image, R.id.btn_show_image})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_select_image:
                mBtImageShow.setVisibility(View.GONE);
                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                intent1.setType("image/jpeg");
                try
                {
                    startActivityForResult(intent1, INTENT_REQUEST_CODE);
                } catch (ActivityNotFoundException e)
                {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_show_image:
                Intent intent2 = new Intent(Intent.ACTION_VIEW);
                intent2.setData(Uri.parse(mImageUrl));
                startActivity(intent2);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if (requestCode == INTENT_REQUEST_CODE)
        {
            if (resultCode == RESULT_OK)
            {
                try
                {
                    InputStream is = getContentResolver().openInputStream(data.getData());
                    uploadImage(getBytes(is));
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public byte[] getBytes(InputStream is) throws IOException
    {
        ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();

        int buffSize = 1024;
        byte[] buff = new byte[buffSize];

        int len = 0;
        while ((len = is.read(buff)) != -1)
        {
            byteBuff.write(buff, 0, len);
        }

        return byteBuff.toByteArray();
    }

    private void uploadImage(byte[] imageBytes)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UploadService uploadService = retrofit.create(UploadService.class);

        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageBytes);

        MultipartBody.Part body = MultipartBody.Part.createFormData("image", "image.jpg", requestFile);
        Call<Response> call = uploadService.uploadImage(body);
        mProgressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<Response>()
        {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response)
            {

                mProgressBar.setVisibility(View.GONE);

                if (response.isSuccessful())
                {

                    Response responseBody = response.body();
                    mBtImageShow.setVisibility(View.VISIBLE);
                    mImageUrl = URL + responseBody.getPath();
                    Snackbar.make(findViewById(R.id.content), responseBody.getMessage(), Snackbar.LENGTH_SHORT).show();

                } else
                {
                    ResponseBody errorBody = response.errorBody();
                    Gson gson = new Gson();
                    try
                    {
                        Response errorResponse = gson.fromJson(errorBody.string(), Response.class);
                        Snackbar.make(findViewById(R.id.content), errorResponse.getMessage(), Snackbar.LENGTH_SHORT).show();

                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t)
            {
                mProgressBar.setVisibility(View.GONE);
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }


}
