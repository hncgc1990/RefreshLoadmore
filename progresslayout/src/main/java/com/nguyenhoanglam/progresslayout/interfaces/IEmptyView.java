package com.nguyenhoanglam.progresslayout.interfaces;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 空数据视图
 */

public interface IEmptyView {

    RelativeLayout init();


    void setView(String text, Drawable drawable, View.OnClickListener listener);
}
