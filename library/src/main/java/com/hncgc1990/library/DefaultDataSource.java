package com.hncgc1990.library;

/**
 * Created by Administrator on 2017-8-31.
 */

public class DefaultDataSource implements IDataSoucre {


    private int  mCurrentPage =1;
    private int mPageCount=3;
    private int mPerPage= 20;


    @Override
    public int getCurrentPage() {
        return mCurrentPage;
    }

    @Override
    public int getPageCount() {
        return mPageCount;
    }

    @Override
    public void setPageCount(int page){
        this.mPageCount=page;
    }

    @Override
    public int getPerPage() {
        return mPerPage;
    }

    @Override
    public void resetPage() {
        mCurrentPage=1;
    }

    @Override
    public void pageUp() {
        mCurrentPage++;
    }

    @Override
    public void pageDown() {
        mCurrentPage--;
    }

    @Override
    public boolean hasMore() {
        return mCurrentPage<mPageCount;
    }

    @Override
    public boolean isRefreah() {
        return mCurrentPage==1;
    }
}
