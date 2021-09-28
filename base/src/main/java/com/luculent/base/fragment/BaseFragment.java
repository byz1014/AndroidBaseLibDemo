package com.luculent.base.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luculent.base.bind.ViewUtils;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = LayoutInflater.from(getContext()).inflate(onLayout(),null);
        ViewUtils.getViewUtils().IndexFindViewById(BaseFragment.class,mView,this);
        onWeight(mView);
        initData();
        return mView;
    }

    abstract int onLayout();

    abstract void onWeight(View v);

    abstract void initData();


    protected void goActivity(Class<?> cla, Bundle bundle) {
        Intent intent = new Intent(getActivity(), cla);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        startActivity(intent);
    }

    protected void goResultActivity(Class<?> cla, Bundle bundle, int code) {
        Intent intent = new Intent(getActivity(), cla);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        startActivityForResult(intent, code);
    }

    protected Bundle getBundle() {
        return getArguments() ;
    }

}
