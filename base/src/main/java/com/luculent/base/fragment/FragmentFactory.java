package com.luculent.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * FragmentFactory
 *
 * @blame Android Team
 */
public class FragmentFactory {
    private FragmentManager fragmentManager;
    private int showRes;
    private List<FragmentBean> fragmentBeans;

    public FragmentFactory(FragmentManager fragmentManager, int showRes) {
        this.fragmentManager = fragmentManager;
        this.showRes = showRes;
        if (fragmentBeans == null) {
            fragmentBeans = new ArrayList<>();
        } else {
            fragmentBeans.clear();
        }

    }

    public FragmentFactory addFragment(Fragment fragment, String tag) {
        fragmentBeans.add(new FragmentBean(fragment, tag));
        return this;
    }

    public void commit() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragmentBeans.size(); i++) {
            fragmentTransaction.add(showRes, fragmentBeans.get(i).mFragment, fragmentBeans.get(i).mFragmentTag);
        }
        for (int i = 0; i < fragmentBeans.size(); i++) {
            if (i == 0) {
                nowFragment = fragmentBeans.get(i).getFragment();
                fragmentTransaction.show(fragmentBeans.get(i).getFragment());
            } else {
                fragmentTransaction.hide(fragmentBeans.get(i).getFragment());
            }

        }

        fragmentTransaction.commit();
    }

    public void commit(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragmentBeans.size(); i++) {
            fragmentTransaction.add(showRes, fragmentBeans.get(i).mFragment, fragmentBeans.get(i).mFragmentTag);
        }
        for (int i = 0; i < fragmentBeans.size(); i++) {
            if (i == index) {
                nowFragment = fragmentBeans.get(i).getFragment();
                fragmentTransaction.show(fragmentBeans.get(i).getFragment());
            } else {
                fragmentTransaction.hide(fragmentBeans.get(i).getFragment());
            }

        }
        fragmentTransaction.commit();
    }


    public void show(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragmentBeans.size(); i++) {
            if (fragmentBeans.get(i).getFragment() == fragment) {
                nowFragment = fragmentBeans.get(i).getFragment();
                fragmentTransaction.show(fragmentBeans.get(i).getFragment());
            } else {
                fragmentTransaction.hide(fragmentBeans.get(i).getFragment());
            }
        }
        fragmentTransaction.commit();
    }

    public void show(String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragmentBeans.size(); i++) {
            if (fragmentBeans.get(i).getFragmentTag().equals(tag)) {
                nowFragment = fragmentBeans.get(i).getFragment();
                fragmentTransaction.show(fragmentBeans.get(i).getFragment());
            } else {
                fragmentTransaction.hide(fragmentBeans.get(i).getFragment());
            }
        }
        fragmentTransaction.commit();
    }


    ArrayList<Fragment> listFragment = new ArrayList<>();

    public void initFragment(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            listFragment.clear();
            for (int i = 0; i < fragmentBeans.size(); i++) {
                listFragment.add(fragmentManager.findFragmentByTag(fragmentBeans.get(i).getFragmentTag()));
            }
            //取出onSaveInstanceState(Bundle outState)里保存离开时的那个界面的tag,恢复到用户离开时的那个Fragment的界面
            String tag = savedInstanceState.getString("lastVisibleFragment");
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            for (int i = 0; i < fragmentBeans.size(); i++) {
                if (fragmentBeans.get(i).getFragmentTag().equals(tag)) {
                    nowFragment = fragmentBeans.get(i).getFragment();
                    fragmentTransaction.show(fragmentBeans.get(i).getFragment());
                } else {
                    fragmentTransaction.hide(fragmentBeans.get(i).getFragment());
                }
            }
            fragmentTransaction.commit();
        } else {
            // 正常创建时
            commit();
        }

    }


    Fragment nowFragment;

    public Fragment getNowFragment() {
        return nowFragment;
    }


}
