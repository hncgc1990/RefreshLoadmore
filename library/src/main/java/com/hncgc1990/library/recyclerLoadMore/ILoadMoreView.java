package com.hncgc1990.library.recyclerLoadMore;

/**
 * Created by Administrator on 2017-9-6.
 */

public interface ILoadMoreView {




    public void onLoading();

    public void onLoadFinish(boolean empty, boolean hasMore);

    public void onWaitToLoadMore();

    public void onLoadError(int errorCode, String errorMessage);


}
