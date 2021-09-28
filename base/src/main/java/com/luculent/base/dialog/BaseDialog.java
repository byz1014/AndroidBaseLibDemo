package com.luculent.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.luculent.base.R;

/**
 * 配置Dialog相关属性
 * 动态添加dialog的Style
 * Dialog点击事件回调
 */
public abstract class BaseDialog extends Dialog implements View.OnClickListener {

    public BaseDialog(@NonNull Context context) {
        this(context, R.style.customDialog);
    }

    public BaseDialog(@androidx.annotation.NonNull Context context, int themeResId ) {
        super(context, themeResId);
    }

    int anim = 0;

    /**
     * 设置Dialog弹出动画
     * @param anim
     */
    public void setAnim(int anim) {
        this.anim = anim;
    }                          

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(onLayout());
        onWeight(this);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        //全屏显示
        windowDeploy(wm.getDefaultDisplay().getWidth(),wm.getDefaultDisplay().getHeight(),anim);
    }

    protected abstract  int onLayout();

    protected DialogCallBackListener mDialogCallBackListener;

    public void setDialogCallBackListener(DialogCallBackListener mDialogCallBackListener) {
        this.mDialogCallBackListener = mDialogCallBackListener;
    }

    protected abstract void onWeight(Dialog dialog);


    @Override
    public void dismiss() {
        if(onCallBackActivityListener!=null){
            onCallBackActivityListener.onActivityResume();
        }
        super.dismiss();
    }

    Window window;

    /**
     * 设置窗口显示
     * @param x 显示宽
     * @param y 显示长
     * @param resAnim 弹出动画
     */
    public void windowDeploy(int x, int y,int resAnim){
          window = getWindow(); //得到对话框
        //
        if(resAnim != 0){
            assert window != null;
            window.setWindowAnimations(resAnim); //设置窗口弹出动画
        }
        assert window != null;
        window.setBackgroundDrawableResource(android.R.color.transparent); //设置对话框背景为透明
        WindowManager.LayoutParams wl = window.getAttributes();
        //根据x，y坐标设置窗口需要显示的位置
        wl.width = x; //x小于0左移，大于0右移
        wl.height = y; //y小于0上移，大于0下移
//            wl.alpha = 0.6f; //设置透明度
        wl.gravity = Gravity.CENTER; //设置重力
        window.setAttributes(wl);
    }

    private OnCallBackActivityListener onCallBackActivityListener;

    public void setOnCallBackActivityListener(OnCallBackActivityListener onCallBackActivityListener) {
        this.onCallBackActivityListener = onCallBackActivityListener;
    }

    public interface OnCallBackActivityListener{
        void onActivityResume();
    }


}
