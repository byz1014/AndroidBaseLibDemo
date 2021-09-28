package com.luculent.base.http;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

/**
 * @Author byz
 * @CreateData 2021/1/11 9:54
 * @blame Android Team
 * @Content
 */
public abstract class LifecycleObserver<T> implements Observer<T> {
    private BaseCallBackListener<T> mBaseCallBackListener;

    public LifecycleObserver( BaseCallBackListener<T> mBaseCallBackListener) {
        this.mBaseCallBackListener = mBaseCallBackListener;
    }

    @Override
    public void onChanged(@Nullable T o) {
        onCallBack(o, mBaseCallBackListener);
    }

    public abstract void onCallBack(@Nullable T o, BaseCallBackListener<T> baseCallBackListener);

}
