package com.hncgc1990.refreshloadmore.view;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hncgc1990.refreshloadmore.R;
import com.nguyenhoanglam.progresslayout.interfaces.IEmptyView;

/**
 * Created by Administrator on 2017-9-4.
 */

public class MyEmptyView implements IEmptyView {


    LayoutInflater mInflater;

    RelativeLayout mRlEmpty;

    Button btnEmpty;
    TextView tvEmpty;

    public MyEmptyView(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    @Override
    public RelativeLayout init() {

        View view = mInflater.inflate(R.layout.layout_empty, null);
        mRlEmpty = (RelativeLayout) view.findViewById(R.id.rl_empty);

        btnEmpty = (Button) view.findViewById(R.id.btn_empty);
        tvEmpty = (TextView) view.findViewById(R.id.tv_empty);


        return mRlEmpty;
    }

    @Override
    public void setView(String text, Drawable drawable, View.OnClickListener listener) {
       btnEmpty.setOnClickListener(listener);
        tvEmpty.setText(text);
    }
}
