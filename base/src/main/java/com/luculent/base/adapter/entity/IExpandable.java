package com.luculent.base.adapter.entity;

import java.util.List;

/**
 * @Author byz
 * @CreateData 2020/10/28 11:09
 */
public interface IExpandable<T> {
    boolean isExpanded();
    void setExpanded(boolean expanded);
    List<T> getSubItems();

    /**
     * Get the level of this item. The level start from 0.
     * If you don't care about the level, just return a negative.
     */
    int getLevel();
}
