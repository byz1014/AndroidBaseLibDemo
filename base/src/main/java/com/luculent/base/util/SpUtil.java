package com.luculent.base.util;

import android.content.SharedPreferences;

import com.luculent.base.BaseApplication;

import java.util.Map;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class SpUtil {
    private static SpUtil mSpUtil;

    private SpUtil() {}

    public static SpUtil getSpUtil() {
        if (mSpUtil == null) {
            synchronized (SpUtil.class) {
                if (mSpUtil == null) {
                    mSpUtil = new SpUtil();
                }
            }
        }
        return mSpUtil;
    }

    private SharedPreferences sharedPreferences;

    public void putString(String key, String message) {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getBaseApplication().getSharedPreferences("lucuSp", MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, message);
        editor.apply();
    }

    public void putInt(String key, int message) {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getBaseApplication().getSharedPreferences("lucuSp", MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, message);
        editor.apply();
    }

    public void putBoolean(String key, boolean message) {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getBaseApplication().getSharedPreferences("lucuSp", MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, message);
        editor.apply();
    }

    public void putFloat(String key, float message) {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getBaseApplication().getSharedPreferences("lucuSp", MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, message);
        editor.apply();
    }

    public void putLong(String key, long message) {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getBaseApplication().getSharedPreferences("lucuSp", MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, message);
        editor.apply();
    }

    public void putStringSet(String key, Set<String> strings) {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getBaseApplication().getSharedPreferences("lucuSp", MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key, strings);
        editor.apply();
    }

    public String getString(String key, String defultValue) {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getBaseApplication().getSharedPreferences("lucuSp", MODE_PRIVATE);
        }
        return sharedPreferences.getString(key, defultValue);
    }


    public Integer getInt(String key, int defultValue) {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getBaseApplication().getSharedPreferences("lucuSp", MODE_PRIVATE);
        }
        return sharedPreferences.getInt(key, defultValue);
    }


    public Long getLong(String key, long defultValue) {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getBaseApplication().getSharedPreferences("lucuSp", MODE_PRIVATE);
        }
        return sharedPreferences.getLong(key, defultValue);
    }


    public Float getString(String key, float defultValue) {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getBaseApplication().getSharedPreferences("lucuSp", MODE_PRIVATE);
        }
        return sharedPreferences.getFloat(key, defultValue);
    }


    public Set<String> getString(String key, Set<String> defultValue) {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getBaseApplication().getSharedPreferences("lucuSp", MODE_PRIVATE);
        }
        return sharedPreferences.getStringSet(key, defultValue);
    }

    public Boolean getBoolean(String key, boolean defultValue) {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getBaseApplication().getSharedPreferences("lucuSp", MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(key, defultValue);
    }

    public Map<String, ?> getAll() {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getBaseApplication().getSharedPreferences("lucuSp", MODE_PRIVATE);
        }
        return sharedPreferences.getAll();
    }


}
