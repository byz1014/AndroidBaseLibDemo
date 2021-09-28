package com.base.luculent;
import android.os.Bundle;
import android.view.View;
import com.luculent.base.BaseActivity;

/**
 * SecondActivity
 *
 * @blame Android Team
 */
public class SecondActivity extends BaseActivity {


    @Override
    protected int onLayout() {
        return R.layout.activity_second;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        findViewById(R.id.tv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void onPermissionResult(int code) {

    }





    @Override
    public void onClick(View v) {

    }

}
