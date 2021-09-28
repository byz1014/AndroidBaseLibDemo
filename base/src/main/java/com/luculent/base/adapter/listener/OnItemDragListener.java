package com.luculent.base.adapter.listener;

import android.support.v7.widget.RecyclerView;

/**
 * @Author byz
 * @CreateData 2020/10/28 11:14
 */
public interface OnItemDragListener {
    void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos);

    void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to);

    void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos);
}
