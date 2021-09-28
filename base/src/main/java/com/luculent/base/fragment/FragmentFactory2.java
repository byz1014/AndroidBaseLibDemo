package com.luculent.base.fragment;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;

public class FragmentFactory2 {


    private static final String TAG = "FragmentFactory";

    private FragmentFactory2() {
        //no instance
    }

    private static final ArrayMap<String, FragmentProvider> providers = new ArrayMap<>();
    private static FragmentProvider emptyFragmentProvider;

    public static void setEmptyFragmentProvider(FragmentProvider emptyFragmentProvider) {
        FragmentFactory2.emptyFragmentProvider = emptyFragmentProvider;
    }

    public static void register(String key, FragmentProvider provider) {
        if (TextUtils.isEmpty(key)) {
            throw new IllegalArgumentException("key can not be empty");
        }
        if (provider == null) {
            throw new IllegalArgumentException("provider can not be null");
        }
        if (providers.get(key) != null) {
            throw new IllegalArgumentException("provider already register for key " + key);
        }

        providers.put(key, provider);
    }
    public static FragmentProvider get(String key) {
        FragmentProvider provider = providers.get(key);
        if (provider == null) {
            Log.e("TAG", "can not found provider for key " + key);
            provider = emptyFragmentProvider;
        }
        return provider;
    }
}
