package com.hncgc1990.library;

/**
 * Created by Administrator on 2017-8-31.
 */

public interface IDataSoucre {

    /**
     * 获取当前页码
     * @return
     */
    int getCurrentPage();


    /**
     * 获取页码总数
     * @return
     */
    int getPageCount();


    /**
     * 设置总页数
     * @return
     */
    void setPageCount(int page);
    /**
     * 获取每页条数
     * @return
     */
    int getPerPage();

    /**
     * 重置页码
     */
    void resetPage();

    /**
     * 页码++
     */
    void pageUp();

    /**
     * 页码--
     */
    void pageDown();


    /**
     * 是否还可以继续加载更多
     *
     * @return
     */
    boolean hasMore();


    boolean isRefreah();
}
