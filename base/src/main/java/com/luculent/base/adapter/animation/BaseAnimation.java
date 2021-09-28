package com.luculent.base.adapter.animation;

import android.animation.Animator;
import android.view.View;

/**
 * @Author byz
 * @CreateData 2020/10/28 11:04
 */
public interface BaseAnimation {
    Animator[] getAnimators(View view);
}
