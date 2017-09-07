package com.hncgc1990.refreshloadmore.helper;


import com.hncgc1990.refreshloadmore.data.PostData;
import com.hncgc1990.refreshloadmore.data.Result;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * 自定义协议的处理
 * Created by Administrator on 2017/4/6.
 */
public class ProtocolHelper {


    public static ObservableTransformer<PostData<List<Result>>, PostData<List<Result>>> applyProtocolHandler() {
        return new ObservableTransformer<PostData<List<Result>>, PostData<List<Result>>>() {
            @Override
            public ObservableSource<PostData<List<Result>>> apply(Observable<PostData<List<Result>>> upstream) {
                return upstream.map(new Function<PostData<List<Result>>, PostData<List<Result>>>() {
                    @Override
                    public PostData apply(PostData postData) throws Exception {

                        if(!postData.getError()){
                            return postData;
                        }else{
                            throw new RuntimeException("wrong response");
                        }
                    }
                });
            }};

    }




}
