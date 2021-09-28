package com.luculent.base.fragment;

import android.support.v4.app.Fragment;

public class FragmentBean {

    Fragment mFragment;
    String mFragmentTag;

    public FragmentBean(Fragment mFragment, String mFragmentTag) {
        this.mFragment = mFragment;
        this.mFragmentTag = mFragmentTag;
    }


    public Fragment getFragment() {
        return mFragment;
    }

    public void setFragment(Fragment mFragment) {
        this.mFragment = mFragment;
    }

    public String getFragmentTag() {
        return mFragmentTag;
    }

    public void setmFragmentTag(String mFragmentTag) {
        this.mFragmentTag = mFragmentTag;
    }
}
