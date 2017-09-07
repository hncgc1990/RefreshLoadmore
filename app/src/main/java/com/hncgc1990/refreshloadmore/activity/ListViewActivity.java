package com.hncgc1990.refreshloadmore.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.hncgc1990.library.RefreshLoadMoreListHelper;
import com.hncgc1990.library.loadMore.LoadMoreListViewContainer;
import com.hncgc1990.refreshloadmore.R;
import com.hncgc1990.refreshloadmore.RetrofitSingle;
import com.hncgc1990.refreshloadmore.adapter.ListAdapter;
import com.hncgc1990.refreshloadmore.data.PostData;
import com.hncgc1990.refreshloadmore.data.PostListInter;
import com.hncgc1990.refreshloadmore.data.Result;
import com.hncgc1990.refreshloadmore.helper.ProtocolHelper;
import com.hncgc1990.refreshloadmore.helper.SchedulerHelper;
import com.nguyenhoanglam.progresslayout.ProgressFrameLayout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;

public class ListViewActivity extends AppCompatActivity {
    @BindView(R.id.ptrFrameLayout)
    PtrClassicFrameLayout ptrFrameLayout;

    @BindView(R.id.loadMoreContainer)
    LoadMoreListViewContainer loadMoreContainer;

    @BindView(R.id.listview)
    ListView listview;


    ListAdapter myAdapter;


    RefreshLoadMoreListHelper mHelper;
    @BindView(R.id.progressLayout)
    ProgressFrameLayout progressLayout;


    public static void startListViewActivity(Context context){
        Intent intent=new Intent(context,ListViewActivity.class);
        context.startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ButterKnife.bind(this);
        myAdapter = new ListAdapter();
        listview.setAdapter(myAdapter);

        //初始化,设置刷新,加载更多的请求
        mHelper = new RefreshLoadMoreListHelper(this, ptrFrameLayout, progressLayout, loadMoreContainer, new RefreshLoadMoreListHelper.LoadDataListener() {
            @Override
            public void doLoadData(int page, int perPage) {
                getListData(page);
            }
        });

        //刷新请求
        mHelper.refresh();

    }

  int count=0;
    private void getListData(int page) {

        Retrofit instance = RetrofitSingle.getInstance();
        PostListInter postListInter = instance.create(PostListInter.class);
        postListInter
                .getPostList(page)
                .map(new Function<PostData<List<Result>>, PostData<List<Result>>>() {
                    @Override
                    public PostData<List<Result>> apply(@NonNull PostData<List<Result>> listPostData) throws Exception {

                        if(count%3==0){
                            count++;
                            throw new RuntimeException("xxxxxxxx");
                        }else{
                            count++;
                            return listPostData;
                        }
                    }
                })
                .delay(2, TimeUnit.SECONDS)
                .compose(SchedulerHelper.<PostData<List<Result>>>applySchedulers())
                .compose(ProtocolHelper.applyProtocolHandler())
                .subscribe(new Observer<PostData<List<Result>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(PostData<List<Result>> value) {
                        Log.d("chen", "onNext");

                        List<Result> results = value.getResults();
                        mHelper.handlerSuccess(myAdapter,results);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("chen", "onError");
                        mHelper.handlerError();
                    }

                    @Override
                    public void onComplete() {
                        Log.d("chen", "onComplete");

                    }
                });


    }
}
