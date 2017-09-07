package com.hncgc1990.library;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.hncgc1990.library.loadMore.LoadMoreUIHandler;
import com.hncgc1990.library.recyclerLoadMore.base.BaseAdapter;
import com.hncgc1990.library.recyclerLoadMore.interfaces.OnLoadMoreListener;
import com.nguyenhoanglam.progresslayout.ProgressFrameLayout;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 下拉刷新加载更多帮助类
 */

public class RefreshLoadMoreRecyclerHelper {


    PtrClassicFrameLayout ptrFrameLayout;

    ProgressFrameLayout progressLayout;

    BaseAdapter loadmoreAdapter;

    LoadDataListener listener;

    IDataSoucre dataSoucre;

    Context context;


    public RefreshLoadMoreRecyclerHelper(Context context, PtrClassicFrameLayout ptrFrameLayout, ProgressFrameLayout progressLayout, BaseAdapter loadmoreAdapter, LoadDataListener listener) {
        this.context=context;
        this.ptrFrameLayout = ptrFrameLayout;
        this.progressLayout=progressLayout;
        this.loadmoreAdapter = loadmoreAdapter;
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

                    if(loadmoreAdapter !=null){
                        return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header) && loadmoreAdapter.isStart();
                    }else{
                        return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
                    }

                }
            });
        }

        // 使用默认样式
        if(loadmoreAdapter !=null){

            loadmoreAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
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
            progressLayout.showEmpty(ContextCompat.getDrawable(context,R.drawable.ic_empty),"暂无数据");
    }

    private void showRefreshError(){
        if(progressLayout!=null)
            progressLayout.showError(ContextCompat.getDrawable(context,R.drawable.ic_no_connection),"网络错误","重试", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadData(true,true);
                }
            });
    }

    private void showRefreshFinish(){
        if(progressLayout!=null)
            progressLayout.showContent();
    }


    private void showLoadMoreLoading(){

    }

    private void showLoadMoreEmpty(){
        if(loadmoreAdapter !=null)
            loadmoreAdapter.loadMoreFinish(true,dataSoucre.hasMore());
    }

    private void showLoadMoreError(String errMsg){
        if(loadmoreAdapter !=null)
            loadmoreAdapter.loadMoreError(0,errMsg);
    }

    private void showLoadMoreFinish(){
        if(loadmoreAdapter !=null)
            loadmoreAdapter.loadMoreFinish(false,dataSoucre.hasMore());
    }



    public void handlerSuccess(IAdapter adapter, List data){
        if(dataSoucre.isRefreah()){
            ptrFrameLayout.refreshComplete();
            if(data.size()==0){
                showRefreshEmpty();
            }else{
                adapter.updateData(dataSoucre.isRefreah(),data);
                showRefreshFinish();
                showLoadMoreFinish();
            }

        }else{
            if(data.size()==0){
                showLoadMoreEmpty();
            }else{
                adapter.updateData(dataSoucre.isRefreah(),data);
                showLoadMoreFinish();
            }
        }

        dataSoucre.pageUp();
    }


    public void handlerError(){

        if(dataSoucre.isRefreah()){
            ptrFrameLayout.refreshComplete();
            showRefreshError();
        }else{
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


    public void setLoadMoreUIHandler(LoadMoreUIHandler handler){
        loadmoreAdapter.setLoadMoreUIHandler(handler);
    }


}
