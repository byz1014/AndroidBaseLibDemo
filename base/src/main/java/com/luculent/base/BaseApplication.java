package com.luculent.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.luculent.base.util.MmkvUtil;
import com.tencent.mmkv.MMKV;

/**
 * BaseApplication
 *
 * @blame Android Team
 */
public abstract class BaseApplication extends Application {
    private static BaseApplication mBaseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication = this;
        MMKV.initialize(this);
        MMKV kv = MMKV.defaultMMKV();
        MmkvUtil.getMmkvUtil().setting(kv);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                onNowCallBackActivity(activity,ActivityStatus.CREATE,bundle);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                onNowCallBackActivity(activity,ActivityStatus.START,null);
            }

            @Override
            public void onActivityResumed(Activity activity) {
                onNowCallBackActivity(activity,ActivityStatus.RESUME,null);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                onNowCallBackActivity(activity,ActivityStatus.PAUSE,null);
            }

            @Override
            public void onActivityStopped(Activity activity) {
                onNowCallBackActivity(activity,ActivityStatus.STOP,null);
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                onNowCallBackActivity(activity,ActivityStatus.SAVEINSTANCESTATE,bundle);
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                onNowCallBackActivity(activity,ActivityStatus.DESTROY,null);
            }

        });



    }

    public static BaseApplication getBaseApplication() {
        return mBaseApplication;
    }

    protected abstract void onNowCallBackActivity(Activity mActivity,ActivityStatus lifeStatus, Bundle bundle);

}
