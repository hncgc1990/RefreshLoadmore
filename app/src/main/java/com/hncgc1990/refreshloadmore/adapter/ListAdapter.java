package com.hncgc1990.refreshloadmore.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hncgc1990.library.IAdapter;
import com.hncgc1990.refreshloadmore.R;
import com.hncgc1990.refreshloadmore.data.Result;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017-8-31.
 */

public class ListAdapter extends BaseAdapter implements IAdapter{


    List<Result> results=new ArrayList<>();

    public ListAdapter() {
    }



    @Override
    public int getCount() {
        Log.d("chen",results.size()+"个");
        return results.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView==null){
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            viewHolder=new ViewHolder(inflate);
            inflate.setTag(viewHolder);
            convertView=inflate;
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

       viewHolder.tvName.setText(position+"."+results.get(position).getDesc());

        return convertView;
    }

    @Override
    public void updateData(boolean isRefresh, List list) {
        if(isRefresh){
            results.clear();
        }
        results.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public Object getData() {
        return results;
    }

    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
