package com.luculent.base.http;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @Author byz
 * @CreateData 2020/10/28 16:27
 * @blame Android Team
 */
public class LuculentHttpUtil extends BaseModel {
    private static LuculentHttpUtil mLuculentHttpUtil;

    private LuculentHttpUtil() {
    }

    public static LuculentHttpUtil getLuculentHttpUtil() {
        if (mLuculentHttpUtil == null) {
            synchronized (LuculentHttpUtil.class) {
                if (mLuculentHttpUtil == null) {
                    mLuculentHttpUtil = new LuculentHttpUtil();
                }
            }
        }
        return mLuculentHttpUtil;
    }


    HashMap<String, MutableLiveData> liveDataHashMap = new HashMap<>();

    public <T> void onLiveDataHttp(Observable<T> observable, LifecycleOwner lifecycleOwner, BaseCallBackListener<T> baseCallBackListener) {

        observable.compose(this.<T>compose()).subscribe(new MyLiveDataObservable<T>(lifecycleOwner, baseCallBackListener) {
            @Override
            public void onSuccess(T t, LifecycleOwner lifecycleOwner, BaseCallBackListener<T> listener) {
                String key = t.getClass().getName();
                if (liveDataHashMap.get(key) == null) {
                    liveDataHashMap.put(key, new MutableLiveData<T>());
                    liveDataHashMap.get(t.getClass().getName()).observe(lifecycleOwner, new LifecycleObserver<T>(listener) {
                        @Override
                        public void onCallBack(@Nullable T o, BaseCallBackListener<T> baseCallBackListener) {
                            baseCallBackListener.onCallBack(getCode(o),o);
                        }
                    });
                }
                liveDataHashMap.get(key).setValue(t);
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

        });
    }


    public <T> void onHttp(Observable<T> observable, BaseCallBackListener<T> baseCallBaclListener) {
        observable.compose(this.<T>compose()).subscribe(new MyObservable<T>(baseCallBaclListener) {

            @Override
            public void onSuccess(T t, BaseCallBackListener<T> baseCallBaclListener) {
                if (baseCallBaclListener != null) {
                    baseCallBaclListener.onCallBack(getCode(t),t);
                }
            }

            @Override
            public void onException(Throwable e, BaseCallBackListener<T> baseCallBaclListener) {
                if (baseCallBaclListener != null) {
                    baseCallBaclListener.onException(e);
                }
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

        });

    }

    private String codeKey = "code";
    private String getCode(Object o) {
        Gson gson = new Gson();
        String json = gson.toJson(o);
        try {
            JSONObject jsonObject = new JSONObject(json);
           return jsonObject.optString(codeKey);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void SettingCodeKey(String code){
        this.codeKey = code;
    }
}
