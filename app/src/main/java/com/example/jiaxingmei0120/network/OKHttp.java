package com.example.jiaxingmei0120.network;

import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OKHttp {

    private static OKHttp instance;
    private static Request request;
    private static Handler handler = new Handler();

    private Interceptor getAppInterceptor(){
        final Interceptor interceptor = new Interceptor(){

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Log.e("++++", "intercept: 拦截前");
                Response response = chain.proceed(request);
                Log.e("++++", "intercept: 拦截后");
                return response;
            }
        };
        return interceptor;
    }

    private OKHttp(){
        File file = new File(Environment.getExternalStorageDirectory(),"cache1");
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(3000, TimeUnit.SECONDS)
                .connectTimeout(3000, TimeUnit.SECONDS)
                .addInterceptor(getAppInterceptor())
                .cache(new Cache(file, 10 * 1024))
                .build();
    }

    public static void getInstance(){
        if (instance == null){
            synchronized (OKHttp.class){
                if (null == instance){
                    instance = new OKHttp();
                }
            }
        }
    }

    //封装get
    public static void doGet(String url, final Class clazz, final GetMessInterface getMessInterface){
        request = new Request.Builder().get().url(url).build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                final Object o = gson.fromJson(response.body().string(), clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        getMessInterface.success(o);
                    }
                });
            }
        });
    }

    public static void doPost(String url, String acc, String pwd, final GetPostInterface getPostInterface){
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody build = new FormBody.Builder().add("phone", acc).add("pwd", pwd).build();
        Request build1 = new Request.Builder().url(url).post(build).build();
        okHttpClient.newCall(build1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                getPostInterface.postSuccess(response.body().string());
            }
        });
    }

    public interface GetMessInterface{
        void success(Object o);
    }
    public interface GetPostInterface{
        void postSuccess(String o);
    }
}
