package com.hncgc1990.refreshloadmore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.hncgc1990.library.DefaultDataSource;
import com.hncgc1990.library.RefreshLoadMoreListHelper;
import com.hncgc1990.library.loadMore.LoadMoreListViewContainer;
import com.hncgc1990.refreshloadmore.adapter.MyAdapter;
import com.hncgc1990.refreshloadmore.data.PostData;
import com.hncgc1990.refreshloadmore.data.PostListInter;
import com.hncgc1990.refreshloadmore.data.Result;
import com.hncgc1990.refreshloadmore.helper.ProtocolHelper;
import com.hncgc1990.refreshloadmore.helper.SchedulerHelper;
import com.nguyenhoanglam.progresslayout.ProgressLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;

public class ListViewActivity extends AppCompatActivity {
    @BindView(R.id.ptrFrameLayout)
    PtrClassicFrameLayout ptrFrameLayout;

    @BindView(R.id.loadMoreContainer)
    LoadMoreListViewContainer loadMoreContainer;

    @BindView(R.id.listview)
    ListView listview;


    MyAdapter myAdapter;


    RefreshLoadMoreListHelper mHelper;
    @BindView(R.id.progressLayout)
    ProgressLayout progressLayout;


    public static void startListViewActivity(Context context){
        Intent intent=new Intent(context,ListViewActivity.class);
        context.startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ButterKnife.bind(this);
        myAdapter = new MyAdapter();
        listview.setAdapter(myAdapter);

        mHelper = new RefreshLoadMoreListHelper(this, ptrFrameLayout, progressLayout, loadMoreContainer, new RefreshLoadMoreListHelper.LoadDataListener() {
            @Override
            public void doLoadData(int page, int perPage) {
                getListData(page);
            }
        });

        mHelper.setDataSoucre(new DefaultDataSource());
        mHelper.refresh();

        int footerViewsCount = listview.getFooterViewsCount();

        Log.d("chen",footerViewsCount+"foot");
    }


    private void getListData(int page) {

        Retrofit instance = RetrofitSingle.getInstance();
        PostListInter postListInter = instance.create(PostListInter.class);
        postListInter
                .getPostList(page)
//                .delay(5, TimeUnit.SECONDS)
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
//                        List<Result> results=new ArrayList<Result>();
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
