package cn.bgs.andfixdemo;

import android.content.Context;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Vincent on 2017/10/27.
 */

public class AndFixUtils {
    private static AndFixUtils instance;
    private PatchManager manager;
    private static final String TAG = "AndFixUtils";
    private AndFixUtils(Context context,String appversion){
        manager=new PatchManager(context);
        manager.init(appversion);
        manager.loadPatch();
    }

    public static AndFixUtils getInstance(Context context,String appversion){
        if (instance==null){
            synchronized (AndFixUtils.class){
                if (instance==null){
                    instance=new AndFixUtils(context,appversion);
                }
            }
        }
       return  instance;
    }

    public void checkAndFix(){
        downLoadPatch();

    }
    private void downLoadPatch() {
        //okHttp的基本使用 --- get方法
        String url = "https://nanke165.github.io/apkpatch";
        //1,创建OKHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //2,创建一个Request
        Request request = new Request.Builder().url(url).build();
        //3,创建一个call对象
        Call call = mOkHttpClient.newCall(request);
        //4,将请求添加到调度中
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e(TAG, "onFailure: "+request.body());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    // TODO: 2017/10/27  将下载的文件 放入储存
                    Log.e(TAG, "onResponse: "+ response.body().string());

                }
            }

        });

    }


}
