package com.hncgc1990.library;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.hncgc1990.library.loadMore.LoadMoreContainer;
import com.hncgc1990.library.loadMore.LoadMoreHandler;
import com.hncgc1990.library.loadMore.LoadMoreListViewContainer;
import com.nguyenhoanglam.progresslayout.ProgressLayout;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 下拉刷新加载更多帮助类
 */

public class RefreshLoadMoreListHelper {


    PtrClassicFrameLayout ptrFrameLayout;

    ProgressLayout progressLayout;

    LoadMoreListViewContainer loadMoreContainer;

    LoadDataListener listener;

    IDataSoucre dataSoucre;

    Context context;


    public RefreshLoadMoreListHelper(Context context, PtrClassicFrameLayout ptrFrameLayout, ProgressLayout progressLayout, LoadMoreListViewContainer loadMoreContainer, LoadDataListener listener) {
        this.context=context;
        this.ptrFrameLayout = ptrFrameLayout;
        this.progressLayout=progressLayout;
        this.loadMoreContainer = loadMoreContainer;
        this.listener=listener;
        dataSoucre=new DefaultDataSource();
        setDataSoucre(dataSoucre);
    }

    public void setDataSoucre(final IDataSoucre dataSoucre) {
        this.dataSoucre = dataSoucre;

        if(ptrFrameLayout!=null){
            ptrFrameLayout.setPtrHandler(new PtrHandler() {
                @Override
                public void onRefreshBegin(PtrFrameLayout frame) {

//                  showRefreshLoading();
                    loadData(false,true);

                }

                @Override
                public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

                    if(loadMoreContainer!=null){
                        return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header) && loadMoreContainer.isStart();
                    }else{
                        return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
                    }

                }
            });
        }

        // 使用默认样式
        if(loadMoreContainer!=null){
            loadMoreContainer.useDefaultFooter();
            loadMoreContainer.setLoadMoreHandler(new LoadMoreHandler() {
                @Override
                public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                    loadData(false,false);
                }
            });
        }


    }

    public void setPageCount(int count){
        dataSoucre.setPageCount(count);
    }

    private  void showRefreshLoading(){
        if(progressLayout!=null)
            progressLayout.showLoading();
    }

    private void showRefreshEmpty(){
        if(progressLayout!=null)
            progressLayout.showEmpty(ContextCompat.getDrawable(context,R.drawable.ptr_rotate_arrow),"暂无数据");
    }

    private void showRefreshError(){
        if(progressLayout!=null)
            progressLayout.showError(ContextCompat.getDrawable(context,R.drawable.ptr_rotate_arrow),"网络错误","重试",null);
    }

    private void showRefreshFinish(){
        if(progressLayout!=null)
            progressLayout.showContent();
    }


    private void showLoadMoreLoading(){

    }

    private void showLoadMoreEmpty(){
        if(loadMoreContainer!=null)
            loadMoreContainer.loadMoreFinish(true,dataSoucre.hasMore());
    }

    private void showLoadMoreError(String errMsg){
        if(loadMoreContainer!=null)
            loadMoreContainer.loadMoreError(0,errMsg);
    }

    private void showLoadMoreFinish(){
        if(loadMoreContainer!=null)
            loadMoreContainer.loadMoreFinish(false,dataSoucre.hasMore());
    }



    public void handlerSuccess(IAdapter adapter, List data){

        adapter.updateData(dataSoucre.isRefreah(),data);

        if(dataSoucre.isRefreah()){
            ptrFrameLayout.refreshComplete();
            if(data.size()==0){
                showRefreshEmpty();
            }else{
                showRefreshFinish();
                showLoadMoreFinish();
            }

        }else{
            if(data.size()==0){
                showLoadMoreEmpty();
            }else{
                showLoadMoreFinish();
            }
        }

        dataSoucre.pageUp();
    }


    public void handlerError(){

        if(dataSoucre.isRefreah()){
            showRefreshError();
        }else{
            //TODO 页码的恢复
            dataSoucre.pageDown();
            showLoadMoreError("网络错误");
        }

    }

    public void refresh() {
        loadData(true,true);
    }

    /**
     *
     * @param isPullDown 是否下拉
     * @param isRefresh  是否刷新
     */
    private void loadData(boolean isPullDown,boolean isRefresh) {
        if(isPullDown)
            showRefreshLoading();
        if(isRefresh)
            dataSoucre.resetPage();
        if(listener!=null){
            listener.doLoadData(dataSoucre.getCurrentPage(),dataSoucre.getPerPage());
        }
    }

    public interface  LoadDataListener{
        void doLoadData(int page, int perPage);
    }


}
