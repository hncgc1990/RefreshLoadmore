package com.hncgc1990.refreshloadmore.data;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/4/5.
 */
public interface PostListInter {


    @GET("data/Android/20/{page}")
    public Observable<PostData<List<Result>>> getPostList(@Path("page") int pageno);
}
