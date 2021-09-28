package com.luculent.base.util;

import android.content.Context;
import android.text.TextUtils;

import java.io.File;

import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * @Author byz
 * @CreateData 2020/12/30 10:14
 * @blame Android Team
 * @Content
 */
public class PictureUtil {
    private static PictureUtil mPictureUtil;


    private String mProvider;

    public void setmProvider(String mProvider) {
        this.mProvider = mProvider;
    }

    private PictureUtil() {
    }

    public static PictureUtil getPictureUtil() {
        if (mPictureUtil == null) {
            synchronized (PictureUtil.class) {
                if (mPictureUtil == null) {
                    mPictureUtil = new PictureUtil();
                }
            }
        }
        return mPictureUtil;
    }


    public void compress(Context mContext, File path, final OnCompressMainListener onCompressMainListener) {
        Luban.with(mContext)
                .load(path)
                .ignoreBy(70)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                       if(onCompressMainListener != null){
                           onCompressMainListener.onCallBack(0,null,null);
                       }
                    }

                    @Override
                    public void onSuccess(File file) {
                        if(onCompressMainListener != null){
                            onCompressMainListener.onCallBack(1,file,null);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(onCompressMainListener != null){
                            onCompressMainListener.onCallBack(2,null,e);
                        }
                    }
                }).launch();
    }


    public  interface    OnCompressMainListener{
        void onCallBack(int status, File file, Throwable e);
    }

}
