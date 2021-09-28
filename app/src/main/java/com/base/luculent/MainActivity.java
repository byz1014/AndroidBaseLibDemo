package com.base.luculent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.luculent.base.BaseActivity;
import com.luculent.base.adapter.BaseQuickAdapter;
import com.luculent.base.bind.FindviewbyId;
import com.luculent.base.dialog.LoaddingDialog;
import com.luculent.base.dialog.LoaddingUtil;
import com.luculent.base.http.BaseCallBackListener;
import com.luculent.base.http.LuculentHttpUtil;
import com.luculent.base.http.RetrofitUtils;
import com.luculent.base.http.TestResult;

import java.util.ArrayList;

/**
 * MainActivity
 *
 * @blame Android Team
 */
public class MainActivity extends BaseActivity {

    @FindviewbyId(value = R.id.rv_body,click = false)
    RecyclerView rv_body;
    TestAdapter mTestAdapter;
    @Override
    protected int onLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mTestAdapter = new TestAdapter(new ArrayList<String>());
        rv_body.setLayoutManager(new LinearLayoutManager(this));
        rv_body.setAdapter(mTestAdapter);
        addView();

        mTestAdapter.setEnableLoadMore(true);
        mTestAdapter.setUpFetchEnable(true);
        mTestAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Log.e("byz_recycle","22222222222222222");
            }
        },rv_body);
        mTestAdapter.setStartUpFetchPosition(0);
        mTestAdapter.setUpFetching(true);
        mTestAdapter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
            @Override
            public void onUpFetch() {
                Log.e("byz_recycle","11111111111111111");
            }
        });

        View view = LayoutInflater.from(this).inflate(R.layout.item_headler,null);
        mTestAdapter.addHeaderView(view);
//        mTestAdapter.isUpFetching()
    }


    @Override
    protected void onPermissionResult(int code) {

    }



    @Override
    public void onClick(View v) {

    }

    ArrayList<String> messageList = new ArrayList<>();
    private void addView(){
        for(int i=0;i<30;i++){
            messageList.add("阿萨德拉克丝           "+i);
        }
        mTestAdapter.setNewData(messageList);
    }



}
