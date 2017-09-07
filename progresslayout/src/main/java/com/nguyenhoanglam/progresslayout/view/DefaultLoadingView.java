package com.nguyenhoanglam.progresslayout.view;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.nguyenhoanglam.progresslayout.interfaces.ILoadingView;
import com.nguyenhoanglam.progresslayout.R;

/**
 * 默认的Loading
 */

public class DefaultLoadingView implements ILoadingView {

    LayoutInflater mInflater;

    RelativeLayout mRlLoading;

    public DefaultLoadingView(LayoutInflater inflater){
        this.mInflater=inflater;
    }



    @Override
    public RelativeLayout init() {

        View view = mInflater.inflate(R.layout.layout_progress_loading, null);
        mRlLoading = (RelativeLayout) view.findViewById(R.id.rl_loading);
        return mRlLoading;
    }

    @Override
    public void setView(String text, String drawable, View.OnClickListener listener) {

    }
}
