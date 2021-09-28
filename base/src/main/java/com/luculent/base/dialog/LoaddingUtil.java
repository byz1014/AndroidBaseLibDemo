package com.luculent.base.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author byz
 * @CreateData 2021/4/14 11:35
 * @blame Android Team
 * @Content
 */
public class LoaddingUtil {

    private static LoaddingUtil mLoaddingUtil;

    private LoaddingUtil(){}

    public static LoaddingUtil getLoaddingUtil(){
        if(mLoaddingUtil==null){
            synchronized (LoaddingUtil.class){
                if(mLoaddingUtil == null){
                    mLoaddingUtil = new LoaddingUtil();
                }
            }
        }
        return mLoaddingUtil;
    }

    private Activity activity;
    public void setNowActivity(Activity nowActivity){
        this.activity = nowActivity;
    }
    HashMap<Activity, LoaddingDialog> activityDialogHashMap = new HashMap<>();

    public void show(String message){
        if(activityDialogHashMap.get(activity) == null){
            LoaddingDialog loaddingDialog = new LoaddingDialog(activity);
            activityDialogHashMap.put(activity,loaddingDialog);
        }
        activityDialogHashMap.get(activity).show(message);
    }

    public void cancelDialog(){
        if(activity!=null){
            if(activityDialogHashMap.get(activity)!=null){
                if(activityDialogHashMap.get(activity).isShowing()){
                    activityDialogHashMap.get(activity).dismiss();
                }
            }
        }
    }
}
