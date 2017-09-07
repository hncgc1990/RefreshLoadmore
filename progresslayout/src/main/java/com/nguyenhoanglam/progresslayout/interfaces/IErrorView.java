package com.nguyenhoanglam.progresslayout.interfaces;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2017-9-4.
 */

public interface IErrorView {

    RelativeLayout init();


    void setView(String errText, Drawable drawable,String btnText, View.OnClickListener listener);
}
