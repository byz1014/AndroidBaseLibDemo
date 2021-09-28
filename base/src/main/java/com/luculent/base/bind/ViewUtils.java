package com.luculent.base.bind;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * 通过反射获取各组件id和是否需要点击事件
 * 根据反射得到的数据，自动获取ID 和添加点击事件
 */
public class ViewUtils {

    private ViewUtils() {
    }

    private static ViewUtils viewUtils;

    public static ViewUtils getViewUtils() {
        if (viewUtils == null) {
            synchronized (ViewUtils.class) {
                if (viewUtils == null) {
                    viewUtils = new ViewUtils();
                }
            }//
        }
        return viewUtils;
    }

    public void IndexFindViewById(Activity curClass) {
        Field[] fields = curClass.getClass().getDeclaredFields();
        for (Field field : fields) {
            FindviewbyId findviewbyId = field.getAnnotation(FindviewbyId.class);
            if (findviewbyId != null) {
                field.setAccessible(true);
                int id = findviewbyId.value();
                boolean isClick = findviewbyId.click();
                try {
                    if (isClick) {
                        curClass.getWindow().getDecorView().findViewById(id).setOnClickListener((View.OnClickListener) curClass);
                    }
                    field.set(curClass, curClass.getWindow().getDecorView().findViewById(id));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void IndexFindViewById(Class<?> curClass, View view, View.OnClickListener onClickListener) {
        Field[] fields = curClass.getDeclaredFields();
        for (Field field : fields) {
            FindviewbyId findviewbyId = field.getAnnotation(FindviewbyId.class);
            if (findviewbyId != null) {
                field.setAccessible(true);
                int id = findviewbyId.value();
                boolean isClick = findviewbyId.click();
                if (isClick) {//
                    view.findViewById(id).setOnClickListener(onClickListener);
                } else {
                    view.findViewById(id);
                }
            }
        }
    }


}
