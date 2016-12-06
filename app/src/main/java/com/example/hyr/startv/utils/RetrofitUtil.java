package com.example.hyr.startv.utils;

import android.util.Log;
import com.example.hyr.startv.api.ApiService;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description:
 * 作者：hyr on 2016/8/29 12:01
 * 邮箱：2045446584@qq.com
 */
public class RetrofitUtil {


    public  static Retrofit.Builder get(String baseUrl){


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog","retrofitBack = "+message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);


        OkHttpClient okHttpClient =new OkHttpClient.Builder().addInterceptor(loggingInterceptor).connectTimeout(5, TimeUnit.SECONDS).build();

        Retrofit.Builder builder = new Retrofit.Builder();

        builder.client(okHttpClient).baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(
            RxJavaCallAdapterFactory.create());

        return builder ;

    }

    public  static ApiService getApi (String baseuRL){

        return  get(baseuRL).build().create(ApiService.class);
    }

}
