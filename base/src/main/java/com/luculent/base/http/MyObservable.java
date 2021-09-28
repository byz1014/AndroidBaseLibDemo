package com.luculent.base.http;

import android.util.Log;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import retrofit2.HttpException;

/**
 * @Author byz
 * @CreateData 2020/11/3 16:42
 */
public  abstract class MyObservable<T> implements Observer<T> {

    private BaseCallBackListener<T> mBaseCallBaclListener;

    public MyObservable(BaseCallBackListener<T> mBaseCallBaclListener) {
        this.mBaseCallBaclListener = mBaseCallBaclListener;
    }


    public MyObservable() {
    }


    @Override
    public void onError(Throwable e) {
        onException(e,mBaseCallBaclListener);

    }




    @Override
    public void onComplete() {

    }

    @Override
    public void onNext(T t) {
        onSuccess( t ,mBaseCallBaclListener);
    }


    public abstract  void onSuccess(T t,BaseCallBackListener<T> baseCallBaclListener);

    public abstract void onException(Throwable e,BaseCallBackListener<T> baseCallBaclListener);


}
