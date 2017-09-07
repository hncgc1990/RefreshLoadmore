package com.hncgc1990.refreshloadmore.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hncgc1990.library.loadMore.LoadMoreUIHandler;
import com.hncgc1990.refreshloadmore.R;

public class MyFooterView extends RelativeLayout implements LoadMoreUIHandler {

    private TextView mTextView;
    private ProgressBar mProgressBar;

    public MyFooterView(Context context) {
        this(context, null);
    }

    public MyFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyFooterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupViews();
    }

    private void setupViews() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_my_footer, this);
        mTextView = (TextView) findViewById(R.id.tv_text);
        mProgressBar= (ProgressBar) findViewById(R.id.pb_loading);
    }

    @Override
    public void onLoading() {
        setVisibility(VISIBLE);
        mTextView.setText("加载中");
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadFinish( boolean empty, boolean hasMore) {
        if (!hasMore) {
            setVisibility(VISIBLE);
            if (empty) {
                mTextView.setText("空");
            } else {
                mTextView.setText("end");
            }
        } else {
            setVisibility(INVISIBLE);
        }
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onWaitToLoadMore() {
        setVisibility(VISIBLE);
        mTextView.setText("更多");
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoadError( int errorCode, String errorMessage) {
        mTextView.setText("失败");
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public ViewGroup getLoadMoreView() {
        return this;
    }
}
