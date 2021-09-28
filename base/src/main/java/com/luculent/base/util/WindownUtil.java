package com.luculent.base.util;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.luculent.base.BaseApplication;


/**
 * WindownUtil
 *
 * @blame Android Team
 */
public class WindownUtil {
    private static WindownUtil mWindownUtil;
    private WindownUtil(){}
    public static WindownUtil getWindownUtil(){
        if(mWindownUtil == null){
            synchronized (WindownUtil.class){
                if(mWindownUtil == null){
                    mWindownUtil = new WindownUtil();
                }
            }
        }
        return mWindownUtil;
    }

    WindowManager wm;
    Display display;
    public int getWidth(){
        if(display == null){
            wm = (WindowManager) BaseApplication.getBaseApplication().getSystemService(Context.WINDOW_SERVICE);
            display = wm.getDefaultDisplay();
        }

        Point size = new Point();
        display.getSize(size);
      return size.x;
    }

    public int getHeight(){
        if(display == null){
            wm = (WindowManager) BaseApplication.getBaseApplication().getSystemService(Context.WINDOW_SERVICE);
            display = wm.getDefaultDisplay();
        }
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }



    /**
     *
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


}
