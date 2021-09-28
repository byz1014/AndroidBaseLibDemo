package com.luculent.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.luculent.base.R;

import androidx.annotation.NonNull;

/**
 * @Author byz
 * @CreateData 2021/4/14 11:03
 * @blame Android Team
 * @Content
 */
public class LoaddingDialog extends BaseDialog {

    public LoaddingDialog(@NonNull Context context ) {
        super(context );
    }

    TextView tv_loadding_message;

    @Override
    protected int onLayout() {
        return R.layout.dialog_loading;
    }

    @Override
    protected void onWeight(Dialog dialog) {
        tv_loadding_message = dialog.findViewById(R.id.tv_loadding_message);
    }

    @Override
    public void onClick(View v) {

    }

    public void show(String message) {
        super.show();
        if(message != null){
            if(message.length()>0){
                tv_loadding_message.setText(message);
            }else {
                tv_loadding_message.setText("加载中...");
            }
        }else {
            tv_loadding_message.setText("加载中...");
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
