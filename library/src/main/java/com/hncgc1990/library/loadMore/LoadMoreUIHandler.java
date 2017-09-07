package com.hncgc1990.library.loadMore;

import android.view.ViewGroup;

public interface LoadMoreUIHandler {

    public void onLoading();

    public void onLoadFinish( boolean empty, boolean hasMore);

    public void onWaitToLoadMore();

    public void onLoadError(int errorCode, String errorMessage);

    public ViewGroup getLoadMoreView();
}