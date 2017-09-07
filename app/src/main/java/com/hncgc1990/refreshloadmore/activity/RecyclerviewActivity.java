package com.hncgc1990.refreshloadmore.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hncgc1990.library.RefreshLoadMoreRecyclerHelper;
import com.hncgc1990.refreshloadmore.R;
import com.hncgc1990.refreshloadmore.RetrofitSingle;
import com.hncgc1990.refreshloadmore.adapter.RecyclerAdapter;
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

public class RecyclerviewActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressLayout)
    ProgressLayout progressLayout;
    @BindView(R.id.ptrFrameLayout)
    PtrClassicFrameLayout ptrFrameLayout;


    RefreshLoadMoreRecyclerHelper mHelper;

    RecyclerAdapter mAdapter;


    public static void startRecyclerviewActivity(Context context){
        Intent intent=new Intent(context,RecyclerviewActivity.class);
        context.startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);

        mAdapter=new RecyclerAdapter(this,null,true);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        mHelper=new RefreshLoadMoreRecyclerHelper(this, ptrFrameLayout, progressLayout, mAdapter, new RefreshLoadMoreRecyclerHelper.LoadDataListener() {
            @Override
            public void doLoadData(int page, int perPage) {
                getListData(page);
            }
        });
        mHelper.refresh();


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
                        mHelper.handlerSuccess(mAdapter,results);
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
