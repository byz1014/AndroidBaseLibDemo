package com.luculent.base.http;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * retrofit数据请求工具类
 *
 * @blame Android Team
 */
public class RetrofitUtils {

    private static String TAG = "RetrofitUtils";
    private static final long READOUT = 180;
    private static final long WRITEOUT = 180;
    //public static final String common_baseUrl = "http://192.168.0.36:8090/"; //政务通王欣本地
    //政务通测试
    public static final String common_baseUrl = "http://219.159.44.172:40043/";
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            // .connectTimeout(TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
            //设置读取超时时间
            .readTimeout(READOUT, TimeUnit.SECONDS)
            //设置写入超时时间
            .writeTimeout(WRITEOUT, TimeUnit.SECONDS)
            //拦截器用于动态切换baseUrl
            .addInterceptor(  new LogIntercetor());


    private static Retrofit.Builder  builder = new Retrofit.Builder()
            .baseUrl(common_baseUrl)
            // 添加Gson转换器
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(buildGson()))
            // 添加Retrofit到RxJava的转换器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());



    /**
     * 存放请求接口类对象集合
     */
    private static Map<Class, Object> apis = new HashMap<>();

    public static <T> T getApi(Class<T> t) {
        Object api = apis.get(t);
        if (api == null) {
            api = builder
                    .client(httpClient.build())
                    .build()
                    .create(t);
            apis.put(t, api);
        }
        return (T) api;
    }

    /**
     * Gson转换器
     */
    private static Gson buildGson() {
        return new GsonBuilder()
                .serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                //对json结果格式化.
                .setPrettyPrinting()
                // 此处可以添加Gson 自定义TypeAdapter
                //.registerTypeAdapter(User.class, new UserTypeAdapter())
                .create();
    }


    private static RetrofitUtils mRetrofitUtils;
    private RetrofitUtils(){}
    public static RetrofitUtils getRetrofitUtils(){
        if(mRetrofitUtils == null){
            synchronized (RetrofitUtils.class){
                if(mRetrofitUtils == null){
                    mRetrofitUtils = new RetrofitUtils();
                }
            }
        }
        return mRetrofitUtils;
    }

    public  RetrofitUtils addIntercetor(Interceptor intercetor){
        httpClient.addInterceptor(intercetor);
        return this;
    }
    public <T> T getIntercetorApi(Class<T> t){
        Object api = apis.get(t);
        if (api == null) {
            api = builder
                    .client(httpClient.build())
                    .build()
                    .create(t);
            apis.put(t, api);
        }
        return (T) api;
    }

    /**
     * 日志拦截器
     */
    private static okhttp3.logging.HttpLoggingInterceptor LogInterceptor() {
        return new okhttp3.logging.HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.w(TAG, "log: " + message);
            }
        })
                .setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
    }

}
