package com.hncgc1990.library.recyclerLoadMore.interfaces;


import com.hncgc1990.library.recyclerLoadMore.ViewHolder;

/**
 * Author: Othershe
 * Time: 2016/8/29 10:48
 */
public interface OnSwipeMenuClickListener<T> {
    void onSwipMenuClick(ViewHolder viewHolder, T data, int position);
}
