package com.luculent.base.dialog;

import android.view.View;

/**
 * @Author byz
 * @CreateData 2020/12/7 15:12
 * @blame Android Team
 * @Content
 */
public class PositionListener implements View.OnClickListener {

    int posoition;
    onViewPositionListener monViewPositionListener;


    public PositionListener(int posoition, onViewPositionListener monViewPositionListener) {
        this.posoition = posoition;
        this.monViewPositionListener = monViewPositionListener;
    }

    @Override
    public void onClick(View v) {
            if(monViewPositionListener !=null){
                monViewPositionListener.onCallBack(posoition,v);
            }
    }


    public interface onViewPositionListener{
        void onCallBack(int posoition,View view);
    }
}
