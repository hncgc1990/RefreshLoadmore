package com.hncgc1990.library.recyclerLoadMore.base;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import com.hncgc1990.library.loadMore.LoadMoreUIHandler;
import com.hncgc1990.library.recyclerLoadMore.LoadMoreController;
import com.hncgc1990.library.recyclerLoadMore.Util;
import com.hncgc1990.library.recyclerLoadMore.ViewHolder;
import com.hncgc1990.library.recyclerLoadMore.interfaces.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Othershe
 * Time: 2016/8/29 09:46
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_COMMON_VIEW = 100001;//普通类型 Item
    public static final int TYPE_FOOTER_VIEW = 100002;//footer类型 Item



    protected Context mContext;
    protected List<T> mDatas;




    protected abstract int getViewType(int position, T data);


    private LoadMoreController mLoadMoreContainer;


    public BaseAdapter(Context context, List<T> datas, boolean isOpenLoadMore) {
        mContext = context;
        mDatas = datas == null ? new ArrayList<T>() : datas;
        mLoadMoreContainer=new LoadMoreController(context);
        mLoadMoreContainer.setOpenLoadMore(isOpenLoadMore);
    }

    public void setLoadMoreUIHandler(LoadMoreUIHandler handler){
        mLoadMoreContainer.setLoadMoreUIHandler(handler);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        switch (viewType) {
            case TYPE_FOOTER_VIEW:
                viewHolder = ViewHolder.create(mLoadMoreContainer.getLoadMoreView());
                break;
        }
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mDatas.size() + getFooterViewCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (isFooterView(position)) {
            return TYPE_FOOTER_VIEW;
        }

        return getViewType(position, mDatas.get(position));
    }

    /**
     * 根据positiond得到data
     *
     * @param position
     * @return
     */
    public T getItem(int position) {
        if (mDatas.isEmpty()) {
            return null;
        }
        return mDatas.get(position);
    }

    /**
     * 是否是FooterView
     *
     * @param position
     * @return
     */
    private boolean isFooterView(int position) {
        return mLoadMoreContainer.isOpenLoadMore() && position >= getItemCount() - 1;
    }

    protected boolean isCommonItemView(int viewType) {
        return  viewType != TYPE_FOOTER_VIEW;
    }


    /**
     * 返回 footer view数量
     *
     * @return
     */
    public int getFooterViewCount() {
        return mLoadMoreContainer.isOpenLoadMore() && !mDatas.isEmpty() ? 1 : 0;
    }

    /**
     * StaggeredGridLayoutManager模式时，FooterView可占据一行
     *
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (isFooterView(holder.getLayoutPosition())) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }

    /**
     * GridLayoutManager模式时， FooterView可占据一行，判断RecyclerView是否到达底部
     *
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager==null) throw new RuntimeException("must set LayoutManager first");

        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) layoutManager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isFooterView(position)) {
                        return gridManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
        mLoadMoreContainer.addScrollListener(recyclerView,layoutManager,this);
    }



    public  int findLastVisibleItemPosition(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(null);
            return Util.findMax(lastVisibleItemPositions);
        }
        return -1;
    }



    /**
     * 刷新加载更多的数据
     *
     * @param datas
     */
    public void setLoadMoreData(List<T> datas) {
        int size = mDatas.size();
        mDatas.addAll(datas);
        notifyItemInserted(size);
    }

    /**
     * 下拉刷新，得到的新数据查到原数据起始
     *
     * @param datas
     */
    public void setData(List<T> datas) {
        mDatas.addAll(0, datas);
        notifyDataSetChanged();
    }

    /**
     * 初次加载、或下拉刷新要替换全部旧数据时刷新数据
     *
     * @param datas
     */
    public void setNewData(List<T> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mDatas.remove(position);
        notifyDataSetChanged();
    }





    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        mLoadMoreContainer.setLoadMoreListener( loadMoreListener);
    }


    public void loadMoreFinish(boolean emptyResult, boolean hasMore) {
       mLoadMoreContainer.loadMoreFinish(emptyResult,hasMore);
    }

    public void loadMoreError(int errorCode, String errorMessage) {
        mLoadMoreContainer.loadMoreError(errorCode,errorMessage);
    }


    /**
     * 判断当前是否滑动到列表第一项
     * @return
     */
    public boolean isStart() {
        return mLoadMoreContainer.isStart();
    }
}
