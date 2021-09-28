package com.luculent.base.http;

import android.arch.lifecycle.LifecycleOwner;
import android.util.Log;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * @Author byz
 * @CreateData 2020/10/30 14:04
 * @blame Android Team
 */
public abstract class MyLiveDataObservable<T> implements Observer<T> {
    LifecycleOwner lifecycleOwner;
    private BaseCallBackListener<T> mBaseCallBaclListener;
    public MyLiveDataObservable(LifecycleOwner lifecycleOwner, BaseCallBackListener<T> baseCallBaclListener) {
        this.lifecycleOwner = lifecycleOwner;
        this.mBaseCallBaclListener = baseCallBaclListener;
    }

    public MyLiveDataObservable( ) {
    }

    @Override
    public void onError(Throwable e) {
        String errorMessage = "系统异常，请稍后重试";
        Log.e("luculent_json","errorObserver:"+e.getMessage());
        if (e instanceof HttpException) {
            //后台返回301 token无效 跳转到登录页面，重新登录
            if (((HttpException) e).code() == 301) {
                return;
            }
            errorMessage = e.getMessage();
        } else if (e instanceof SocketTimeoutException) {
            errorMessage = "服务器连接超时，请稍后重试";
        } else if (e instanceof ConnectException) {
            errorMessage = "服务器链接失败，请稍后重试";
        }
    }


    @Override
    public void onComplete() {

    }

    @Override
    public void onNext(T t) {
        onSuccess( t ,lifecycleOwner, mBaseCallBaclListener );
    }


    public abstract void onSuccess( T t,LifecycleOwner lifecycleOwner,BaseCallBackListener<T> listener);


}
