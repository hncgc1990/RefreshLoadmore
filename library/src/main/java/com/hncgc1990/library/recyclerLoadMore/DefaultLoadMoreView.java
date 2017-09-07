package com.hncgc1990.library.recyclerLoadMore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hncgc1990.library.R;

/**
 * Created by Administrator on 2017-9-6.
 */

public class DefaultLoadMoreView extends RelativeLayout implements ILoadMoreView {

    private TextView mTextView;

    public DefaultLoadMoreView(Context context) {
        this(context, null);
    }

    public DefaultLoadMoreView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DefaultLoadMoreView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupViews();
    }

    private void setupViews() {
        LayoutInflater.from(getContext()).inflate(R.layout.cube_views_load_more_default_footer, this);
        mTextView = (TextView) findViewById(R.id.cube_views_load_more_default_footer_text_view);
    }


    @Override
    public void onLoading() {
        setVisibility(VISIBLE);
        mTextView.setText("加载中...");
    }

    @Override
    public void onLoadFinish(boolean empty, boolean hasMore) {
        if (!hasMore) {
            setVisibility(VISIBLE);
            if (empty) {
                mTextView.setText("空数据");
            } else {
                mTextView.setText("全部数据加载完成");
            }
        } else {
            setVisibility(INVISIBLE);
        }
    }

    @Override
    public void onWaitToLoadMore() {
        setVisibility(VISIBLE);
        mTextView.setText("点击加载更多");
    }

    @Override
    public void onLoadError(int errorCode, String errorMessage) {
        mTextView.setText("加载失败,点击重试");
    }
}
