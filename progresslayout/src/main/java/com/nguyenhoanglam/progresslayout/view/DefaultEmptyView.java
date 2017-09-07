package com.nguyenhoanglam.progresslayout.view;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nguyenhoanglam.progresslayout.interfaces.IEmptyView;
import com.nguyenhoanglam.progresslayout.R;

/**
 * 默认的空数据布局
 */

public class DefaultEmptyView implements IEmptyView {


    LayoutInflater mInflater;

    RelativeLayout mRlEmpty;

    ImageView ivEmpty;
    TextView tvEmpty;

    public DefaultEmptyView(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    @Override
    public RelativeLayout init() {

        View view = mInflater.inflate(R.layout.layout_progress_empty, null);
        mRlEmpty = (RelativeLayout) view.findViewById(R.id.rl_empty);

        ivEmpty = (ImageView) view.findViewById(R.id.iv_empty);
        tvEmpty = (TextView) view.findViewById(R.id.tv_empty);

        return mRlEmpty;
    }

    @Override
    public void setView(String text, Drawable drawable, View.OnClickListener listener) {
        if (drawable != null) {
            ivEmpty.setImageDrawable(drawable);
            ivEmpty.setVisibility(View.VISIBLE);
        } else {
            ivEmpty.setVisibility(View.GONE);
        }
        tvEmpty.setText(text);
    }
}
