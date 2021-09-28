package com.luculent.base.util;

import android.util.Log;

public class LogUtil {

    public static boolean IsOpenLog = true;

    public static void e(String TAG, String message) {
        if (IsOpenLog) {
            Log.e(TAG, message);
        }
    }

    public static void i(String TAG, String message) {
        if (IsOpenLog) {
            Log.i(TAG, message);
        }
    }

    public static void w(String TAG, String message) {
        if (IsOpenLog) {
            Log.w(TAG, message);
        }
    }

    public static void d(String TAG, String message) {
        if (IsOpenLog) {
            Log.d(TAG, message);
        }
    }


}
