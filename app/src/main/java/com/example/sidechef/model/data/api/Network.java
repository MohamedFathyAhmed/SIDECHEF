package com.example.sidechef.model.data.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;

import java.io.IOException;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class  Network{

    private volatile static Retrofit instance = null;
    public static synchronized Retrofit getInstance(Context context) {
        Interceptor onlineInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Response response = chain.proceed(chain.request());
                int maxAge = 60;
                return response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")
                        .build();
            }
        };
        Interceptor offlineInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!isInternetAvailable(context)) {
                    int maxStale = 60 * 60 * 24 * 1; // Offline cache available for 1 days
                    request = request.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return chain.proceed(request);
            }
        };

        long cashSize = 10 * 1024 * 1024;  // 10 MB
        Cache cache = new Cache(context.getCacheDir(), cashSize);

        OkHttpClient  client = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(offlineInterceptor)
                //.addNetworkInterceptor(onlineInterceptor)
                .build();
        if (instance == null)
            instance = new Retrofit.Builder()
                    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                //  .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
            return instance;
    }
    public static boolean isInternetAvailable(Context context) {
        boolean isConnected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected())
            isConnected = true;
        return isConnected;
    }


}