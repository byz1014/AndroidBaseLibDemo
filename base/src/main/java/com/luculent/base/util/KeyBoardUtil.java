package com.luculent.base.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @Author byz
 * @CreateData 2021/3/5 9:04
 * @blame Android Team
 * https://www.jianshu.com/p/1ed19ff1ba76
 * @Content
 */
public class KeyBoardUtil {
    private static KeyBoardUtil mKeyBoardUtil;

    private KeyBoardUtil() {
    }

    public static KeyBoardUtil getKeyBoardUtil(){
        if(mKeyBoardUtil == null){
            synchronized (KeyBoardUtil.class) {
                mKeyBoardUtil = new KeyBoardUtil();
            }
        }
        return mKeyBoardUtil;
    }

    /**
     * 1、inputMethodManager.showSoftInput(xx)
     *   一般来说需要传入的View是EditText 类型的。如果传入其它View，需要进行额外的操作才能弹出键盘。
     *   假设当传入的View是Button类型时，需要设置Button.setFocusableInTouchMode(true)，此时能够弹出键盘。
     *   比较完善的做法是：还需要在onTouchEvent(xx)里弹出键盘、需要将Button与键盘关联。
     *   实际上就是模仿EditText的工作，系统都提供了EditText接收输入字符，
     *   没必要自己再整一套，因此弹出键盘时通常传入EditText。
     *
     * 2、inputMethodManager.showSoftInput(xx) 、inputMethodManager. hideSoftInputFromWindow(xx)
     *    两个方法的最后一个参数用来匹配关闭键盘时判断当初弹出键盘时传入的类型，一般填0即可。
     *
     * 3、inputMethodManager. hideSoftInputFromWindow(xx)
     *    第一个参数传入的IBinder windowToken类型。每个Activity创建时候会生成windowToken，该值存储在AttachInfo里，
     *    因此对于同一个Activity里的ViewTree，每个View持有的windowToken 都是指向通过一个对象。
     *    因为同一个ViewTree里的windowToken都是一致的，因此不一定要传入EditText，
     *    可以传入Button等，只要属于同一个ViewTree即可。
     *
     *    SOFT_INPUT_ADJUST_UNSPECIFIED
     *    不指定调整方式，系统自行决定使用哪种调整方式
     *    SOFT_INPUT_ADJUST_RESIZE
     *    调整方式为布局需要重新计算大小适配当前可见区域
     *    SOFT_INPUT_ADJUST_PAN
     *    调整方式为布局需要整体移动
     *    SOFT_INPUT_ADJUST_NOTHING
     *    不做任何操作
     *
     * @param view
     */

    public void showSoftInput(View view) {
        if (view == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(view, 0);
        }
    }

    public void hideSoftInput(View view) {
        if (view == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    private int mSoftInputHeight = 0;
    private boolean softInputHeightChanged = false;

    private boolean isNavigationBarShow = false;
    private int navigationHeight = 0;

    private View anyView;
    private ISoftInputChanged listener;
    private boolean isSoftInputShowing = false;

    public interface ISoftInputChanged {
        void onChanged(boolean isSoftInputShow, int softInputHeight, int viewOffset);
    }

    public void attachSoftInput(final View anyView, final ISoftInputChanged listener) {
        if (anyView == null || listener == null) {
            return;
        }

        //根View
        final View rootView = anyView.getRootView();
        if (rootView == null) {
            return;
        }

        navigationHeight = getNavigationBarHeight(anyView.getContext());

        //anyView为需要调整高度的View，理论上来说可以是任意的View
        this.anyView = anyView;
        this.listener = listener;

        rootView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {


            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                //对于Activity来说，该高度即为屏幕高度
                int rootHeight = rootView.getHeight();
                Rect rect = new Rect();
                //获取当前可见部分，默认可见部分是除了状态栏和导航栏剩下的部分
                rootView.getWindowVisibleDisplayFrame(rect);

                if (rootHeight - rect.bottom == navigationHeight) {
                    //如果可见部分底部与屏幕底部刚好相差导航栏的高度，则认为有导航栏
                    isNavigationBarShow = true;
                } else if (rootHeight - rect.bottom == 0) {
                    //如果可见部分底部与屏幕底部平齐，说明没有导航栏
                    isNavigationBarShow = false;
                }

                //cal softInput height
                boolean isSoftInputShow = false;
                int softInputHeight = 0;
                //如果有导航栏，则要去除导航栏的高度
                int mutableHeight = isNavigationBarShow == true ? navigationHeight : 0;
                if (rootHeight - mutableHeight > rect.bottom) {
                    //除去导航栏高度后，可见区域仍然小于屏幕高度，则说明键盘弹起了
                    isSoftInputShow = true;
                    //键盘高度
                    softInputHeight = rootHeight - mutableHeight - rect.bottom;
                    if (  mSoftInputHeight != softInputHeight) {
                        softInputHeightChanged = true;
                          mSoftInputHeight = softInputHeight;
                    } else {
                        softInputHeightChanged = false;
                    }
                }

                //获取目标View的位置坐标
                int[] location = new int[2];
                anyView.getLocationOnScreen(location);

                //条件1减少不必要的回调，只关心前后发生变化的
                //条件2针对软键盘切换手写、拼音键等键盘高度发生变化
                if (isSoftInputShowing != isSoftInputShow || (isSoftInputShow && softInputHeightChanged)) {
                    if (listener != null) {
                        //第三个参数为该View需要调整的偏移量
                        //此处的坐标都是相对屏幕左上角(0,0)为基准的
                        listener.onChanged(isSoftInputShow, softInputHeight, location[1] + anyView.getHeight() - rect.bottom);
                    }

                    isSoftInputShowing = isSoftInputShow;
                }
            }
        });
    }


    public static int getNavigationBarHeight(Context context) {
        if (context == null) {
            return 0;
        }
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

}


