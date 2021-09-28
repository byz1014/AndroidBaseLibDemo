package com.luculent.base.http;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @Author byz
 * @CreateData 2021/1/20 16:23
 * @blame Android Team
 * @Content
 */
public class RetrofitFactory {

    private static RetrofitFactory mRetrofitFactory;
    private RetrofitFactory(){}
    public static RetrofitFactory getRetrofitFactory(){
        if(mRetrofitFactory == null){
            synchronized (RetrofitFactory.class){
                if(mRetrofitFactory == null){
                    mRetrofitFactory = new RetrofitFactory();
                }
            }
        }
        return mRetrofitFactory;
    }





    public static OkHttpClient.Builder getOkHttpBuilder(){
        return new OkHttpClient.Builder()
                // .connectTimeout(TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
                //设置读取超时时间
                .readTimeout(READOUT, TimeUnit.SECONDS)
                //设置写入超时时间
                .writeTimeout(WRITEOUT, TimeUnit.SECONDS)
                //拦截器用于动态切换baseUrl
                .addInterceptor(  new LogIntercetor());
    }


    private static final long READOUT = 180;
    private static final long WRITEOUT = 180;
    public static final String common_baseUrl = "http://219.159.44.172:40043/";
//    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
//            // .connectTimeout(TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
//            //设置读取超时时间
//            .readTimeout(READOUT, TimeUnit.SECONDS)
//            //设置写入超时时间
//            .writeTimeout(WRITEOUT, TimeUnit.SECONDS)
//            //拦截器用于动态切换baseUrl
//            .addInterceptor(  new LogIntercetor());


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
                    .client(getOkHttpBuilder().build())
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
}
