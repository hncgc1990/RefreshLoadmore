package com.hncgc1990.refreshloadmore.adapter;

import android.content.Context;

import com.hncgc1990.library.IAdapter;
import com.hncgc1990.library.recyclerLoadMore.ViewHolder;
import com.hncgc1990.library.recyclerLoadMore.base.CommonBaseAdapter;
import com.hncgc1990.refreshloadmore.R;
import com.hncgc1990.refreshloadmore.data.Result;

import java.util.List;

/**
 * Created by Administrator on 2017-9-7.
 */

public class RecyclerAdapter extends CommonBaseAdapter<Result> implements IAdapter<List<Result>>{
    public RecyclerAdapter(Context context, List<Result> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, Result data, int position) {
          holder.setText(R.id.tv_name,data.getDesc());
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_list;
    }

    @Override
    public void updateData(boolean isRefresh, List list) {
        if(isRefresh){
            setNewData(list);
        }else{
            setLoadMoreData(list);
        }
    }

    @Override
    public List<Result> getData() {
        return mDatas;
    }
}
