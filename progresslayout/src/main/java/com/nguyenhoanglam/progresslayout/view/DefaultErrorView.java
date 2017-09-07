package com.nguyenhoanglam.progresslayout.view;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nguyenhoanglam.progresslayout.interfaces.IErrorView;
import com.nguyenhoanglam.progresslayout.R;

/**
 * 默认的错误视图
 */

public class DefaultErrorView implements IErrorView {

    ImageView ivError;
    TextView tvError;
    Button btnError;
    LayoutInflater mInflater;

    RelativeLayout mRlEmpty;


    public DefaultErrorView(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    @Override
    public RelativeLayout init() {

        View view = mInflater.inflate(R.layout.layout_progress_error, null);
        mRlEmpty = (RelativeLayout) view.findViewById(R.id.rl_error);

        ivError = (ImageView) view.findViewById(R.id.iv_error);
        tvError = (TextView) view.findViewById(R.id.tv_error);
        btnError = (Button) view.findViewById(R.id.btn_error);


        return mRlEmpty;
    }

    @Override
    public void setView(String errText, Drawable drawable, String btnText, View.OnClickListener listener) {
        if (drawable != null) {
            ivError.setImageDrawable(drawable);
            ivError.setVisibility(View.VISIBLE);
        } else {
            ivError.setVisibility(View.GONE);
        }
        tvError.setText(errText);
        btnError.setText(btnText);
        btnError.setOnClickListener(listener);
    }


}
