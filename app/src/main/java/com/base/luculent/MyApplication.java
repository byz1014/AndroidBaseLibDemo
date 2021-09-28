package com.base.luculent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.luculent.base.ActivityStatus;
import com.luculent.base.BaseApplication;
import com.luculent.base.util.MmkvUtil;

/**
 * @Author byz
 * @CreateData 2020/10/28 9:08
 * @blame Android Team
 */
public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void onNowCallBackActivity(Activity mActivity, ActivityStatus lifeStatus, Bundle bundle) {
        Log.e("byz_Activity",mActivity.getClass().getName()+"     "+lifeStatus.getStatus());
    }
}
