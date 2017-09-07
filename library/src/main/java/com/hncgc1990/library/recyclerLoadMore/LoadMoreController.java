package com.hncgc1990.library.recyclerLoadMore;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.hncgc1990.library.recyclerLoadMore.base.BaseAdapter;
import com.hncgc1990.library.recyclerLoadMore.interfaces.OnLoadMoreListener;


/**
 * Created by Administrator on 2017-9-6.
 */

public class LoadMoreController {

    private boolean mOpenLoadMore;//是否开启加载更多

    private boolean isAutoLoadMore = true;//是否自动加载，当数据不满一屏幕会自动加载


    private boolean mIsLoading;
    private boolean mHasMore = false;
    private boolean mAutoLoadMore = true;
    private boolean mLoadError = false;
    private boolean mListEmpty = true;

    private boolean mIsEnd =false;


    private OnLoadMoreListener mLoadMoreListener;

    private DefaultLoadMoreView mLoadMoreView;

    private RecyclerView mRecyclerView;

    public LoadMoreController(Context context){
        mLoadMoreView=new DefaultLoadMoreView(context);
        mLoadMoreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollLoadMore();
            }
        });
    }




    /**
     * 添加滚动监听
     * @param recyclerView
     * @param layoutManager
     * @param adapter
     */
    public void addScrollListener(RecyclerView recyclerView, final RecyclerView.LayoutManager layoutManager, final BaseAdapter adapter){
        this.mRecyclerView=recyclerView;
        if (!mOpenLoadMore ) {
            return;
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (mIsEnd) {
                        onReachBottom();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (adapter.findLastVisibleItemPosition(layoutManager) + 1 == adapter.getItemCount()) {
                    mIsEnd = true;
                } else if (isAutoLoadMore) {
                    mIsEnd = false;
                }
            }
        });
    }


    private void onReachBottom() {
        // if has error, just leave what it should be
        if (mLoadError) {
            return;
        }
        if (mAutoLoadMore) {
            scrollLoadMore();
        } else {
            if (mHasMore) {
                mLoadMoreView.onWaitToLoadMore();
            }
        }
    }

    private void scrollLoadMore() {

        if (mIsLoading) {
            return;
        }

        // no more content and also not load for first page
        if (!mHasMore) {
            return;
        }

        mIsLoading = true;

        if (mLoadMoreView != null) {
            mLoadMoreView.onLoading();
        }
        if (null != mLoadMoreListener) {
            mLoadMoreListener.onLoadMore();
        }
    }






    public void loadMoreFinish(boolean emptyResult, boolean hasMore) {
        mLoadError = false;
        mListEmpty = emptyResult;
        mIsLoading = false;
        mHasMore = hasMore;

        if (mLoadMoreView != null) {
            mLoadMoreView.onLoadFinish(emptyResult, hasMore);
        }
    }

    public void loadMoreError(int errorCode, String errorMessage) {
        mIsLoading = false;
        mLoadError = true;
        if (mLoadMoreView != null) {
            mLoadMoreView.onLoadError( errorCode, errorMessage);
        }
    }




    public boolean isOpenLoadMore() {
        return mOpenLoadMore;
    }

    public void setOpenLoadMore(boolean mOpenLoadMore) {
        this.mOpenLoadMore = mOpenLoadMore;
    }


    public OnLoadMoreListener getLoadMoreListener() {
        return mLoadMoreListener;
    }

    public void setLoadMoreListener(OnLoadMoreListener mLoadMoreListener) {
        this.mLoadMoreListener = mLoadMoreListener;
    }

    public boolean isAutoLoadMore() {
        return isAutoLoadMore;
    }

    public void setAutoLoadMore(boolean autoLoadMore) {
        isAutoLoadMore = autoLoadMore;
    }

    public DefaultLoadMoreView getLoadMoreView() {
        return mLoadMoreView;
    }

    public boolean isStart() {

        RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        //判断是当前layoutManager是否为LinearLayoutManager
        // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;

            //获取第一个可见view的位置
            int firstItemPosition = linearManager.findFirstCompletelyVisibleItemPosition();
            return firstItemPosition==0;
        }else if(layoutManager instanceof StaggeredGridLayoutManager){
            StaggeredGridLayoutManager gridlayoutManager= (StaggeredGridLayoutManager) layoutManager;

            int[] firstItemPosition=new int[gridlayoutManager.getSpanCount()];
            gridlayoutManager.findFirstCompletelyVisibleItemPositions(firstItemPosition);
            return firstItemPosition[0]==0;
        } else {
            return false;
        }

    }
}
