package com.xiaokun.xiusou.demo6.Activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;

import com.socks.library.KLog;
import com.xiaokun.xiusou.demo6.Application.MyApplication;
import com.xiaokun.xiusou.demo6.R;
import com.xiaokun.xiusou.demo6.Utils.DES3;
import com.yuyh.library.utils.StringUtils;
import com.yuyh.library.utils.data.HexUtils;
import com.yuyh.library.utils.data.safe.MD5;
import com.yuyh.library.utils.io.FileUtils;
import com.yuyh.library.utils.io.IOUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Bind;
import dalvik.system.DexClassLoader;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/12/27 0027.
 */

public class HotUpdateActivity extends AppCompatActivity
{
    @Bind(R.id.btn_hot_update)
    Button button;
    @Bind(R.id.btn_hot_regist)
    Button btn_regist;
    private Context mContext;
    private byte[] dexBytes;
    private String deviceId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_update);
        ButterKnife.bind(this);
        mContext = this;
        KLog.d(this.getPackageName());

        final TelephonyManager tm = (TelephonyManager) mContext
                .getSystemService(Context.TELEPHONY_SERVICE);
        deviceId = tm.getDeviceId();


        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (dexBytes == null)
                {
                    getData("1234567", deviceId);
                } else
                {
                    File write = write(dexBytes);
                    dynamicLoad(write, "init");
                }
            }
        });

        btn_regist.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                File write = write(dexBytes);
                dynamicLoad(write, "regist");
//                regist(write, "regist");
            }
        });
    }

    private void getData(String appkey, final String deviceId)
    {
        final String appid = StringUtils.RandomString(32);
        int bs[] = new int[32];//i的取值
        final ArrayList<Integer> integers = new ArrayList<>();
        final int zs[] = new int[2];
        for (int i = 0; i < appid.length(); i++)
        {
            char a = appid.charAt(i);
            int b = (int) a;
            bs[i] = b;
        }
        for (int i = 0; i < 8; i++)
        {
            integers.add((bs[i * 4] + bs[i * 4 + 1] + bs[i * 4 + 2] + bs[i * 4 + 3]) % 32);
        }
        zs[0] = (bs[2] + bs[6] + bs[10] + bs[14]) % 32;
        zs[1] = (bs[18] + bs[22] + bs[26] + bs[30]) % 32;
        KLog.d(zs[0] + ";" + zs[1]);

        OkHttpUtils.post().addParams("appid", appid).addParams("appkey", appkey).addParams
                ("deviceid", deviceId).url("http://z.01808" +
                ".cn/api/requeststr/").build().execute(new StringCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id)
            {
            }

            @Override
            public void onResponse(String response, int id)
            {
                KLog.d(response);
                char[] mResponse = appid.toCharArray();
                String encode = "xiusou";
                String substring = null;
                for (int i = 0; i < mResponse.length; i++)
                {
                    if (!integers.contains(i))
                    {
                        continue;
                    }
                    for (int z = 0; z < mResponse.length; z++)
                    {
                        if (!(z == zs[0] || z == zs[1]))
                        {
                            continue;
                        }
                        mResponse = appid.toCharArray();
                        char a = mResponse[i];
                        mResponse[i] = mResponse[z];
                        mResponse[z] = a;
                        String valueOf = String.valueOf(mResponse);
                        try
                        {
                            encode = DES3.decode(response, MD5.md5String(valueOf));
                        } catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        substring = encode.substring(0, 6);

                        KLog.d(substring);
                        KLog.d(valueOf);
                        if (substring.equals("646578"))
                        {
                            KLog.d(encode);
                            //将最后的字符MD5后存入本地
                            String md5String = MD5.md5String(valueOf);
                            File dir = mContext.getFilesDir();
                            File createFile = createFile(dir.getAbsolutePath(), MD5.md5String
                                    (deviceId) + ".jpg");
                            FileUtils.writeFileSdcard(createFile.getAbsolutePath(),
                                    md5String, false);
                            KLog.d(md5String);
                            MyApplication.toastUtils.showToast(FileUtils
                                    .getFileOutputString(createFile.getPath()).trim());
                            //16进制字符串转转换成字节数组
                            dexBytes = HexUtils.hexStringToBytes(encode);
                            //写入文件中
                            File write = write(dexBytes);
//                            File file = createFile(Environment.getExternalStorageDirectory
//                                    ().getAbsolutePath(), "xiaokun.dex");
//
//                            try {
//                                byte[] fileBytes = IOUtils.getFileBytes(file);
//                                String hexStr = HexUtils.encodeHexStr(fileBytes);
//                                KLog.d(hexStr);
//                                KLog.d(hexStr.equals(encode));
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
                            //动态加载
                            dynamicLoad(write, "init");
                            break;
                        }
                    }
                    if (substring.equals("646578"))
                    {
                        break;
                    }
                }

            }
        });
    }

    /**
     * 将字节数组写入文件中
     *
     * @param bytes
     */
    private File write(byte[] bytes)
    {
        File file = createFile(Environment.getExternalStorageDirectory
                ().getAbsolutePath(), "xiusou.dex");
        try
        {
            InputStream inputStream = IOUtils.Byte2InputStream(bytes);
            String s = IOUtils.stream2file(inputStream, Environment.getExternalStorageDirectory
                    ().getAbsolutePath() + "/xiusou.dex");
            KLog.d(s);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 创建文件
     *
     * @param absolutePath
     * @param name
     * @return
     */
    private File createFile(String absolutePath, String name)
    {
        File file = new File(absolutePath, name);
        if (!file.exists())
        {
            try
            {
                file.createNewFile();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 动态加载
     *
     * @param file
     * @param name
     */
    private void dynamicLoad(File file, String name)
    {
        File dexOutputDir = mContext.getDir("dex", 0);
        KLog.e("dexOutputDir:" + dexOutputDir.getAbsolutePath());
        DexClassLoader cl = new DexClassLoader(file
                .getAbsolutePath(),
                dexOutputDir.getAbsolutePath(), null,
                getClassLoader());
        Class mLoadClass = null;

        try
        {
            mLoadClass = cl.loadClass("com.example.xiusou_regist" +
                    ".RegisterTool");
            Method[] methods = mLoadClass.getDeclaredMethods();

            for (int y = 0; y < methods.length; y++)
            {
                KLog.d(methods[y].toString());
            }

            Method method = mLoadClass.getDeclaredMethod("init",
                    Context.class, String.class, String.class);
            method.setAccessible(true);
            method.invoke(mContext, new Object[]{mContext,
                    "1234567", name});
        } catch (Exception exception)
        {
            exception.printStackTrace();
            KLog.d(exception);
        }
    }

    /**
     * 动态加载
     *
     * @param file
     * @param name
     */
    private void regist(File file, String name)
    {
        File dexOutputDir = mContext.getDir("dex", 0);
        KLog.e("dexOutputDir:" + dexOutputDir.getAbsolutePath());
        DexClassLoader cl = new DexClassLoader(file
                .getAbsolutePath(),
                dexOutputDir.getAbsolutePath(), null,
                getClassLoader());
        Class mLoadClass = null;

        try
        {
            mLoadClass = cl.loadClass("com.example.xiusou_regist" +
                    ".RegisterTool");
            Method[] methods = mLoadClass.getDeclaredMethods();

            for (int y = 0; y < methods.length; y++)
            {
                KLog.d(methods[y].toString());
            }

            Method method = mLoadClass.getDeclaredMethod(name,
                    Context.class,
                    String.class);
            method.setAccessible(true);
            method.invoke(mContext, new Object[]{mContext,
                    "1234567"});
        } catch (Exception exception)
        {
            exception.printStackTrace();
            KLog.d(exception);
        }
    }

}
