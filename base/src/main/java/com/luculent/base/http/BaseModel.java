package com.luculent.base.http;


import com.luculent.base.util.NetworkUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BaseModel {
    //管理observer 防止内存泄漏
    private CompositeDisposable disposables = new CompositeDisposable();

    public void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    public void clear() {
        disposables.clear();
    }

    //切换线程 io线程请求 主线程接收
    protected <T> ObservableTransformer<T, T> compose() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                // 可添加网络连接判断等
                                if (!NetworkUtils.isConnected()) {
                                    if (!disposable.isDisposed()) {
                                        disposable.dispose();
                                    }
                                }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
