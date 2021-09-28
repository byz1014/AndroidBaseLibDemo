package com.base.luculent;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.luculent.base.adapter.BaseQuickAdapter;
import com.luculent.base.adapter.BaseViewHolder;

import java.util.List;

public class TestAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public TestAdapter(@Nullable List<String> data) {
        super(R.layout.item_layout, data);
    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name,item);
    }
}
