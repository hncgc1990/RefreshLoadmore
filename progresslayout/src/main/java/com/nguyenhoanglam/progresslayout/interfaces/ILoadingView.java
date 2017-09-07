package com.nguyenhoanglam.progresslayout.interfaces;

import android.view.View;
import android.widget.RelativeLayout;

/**
 * 加载View
 */

public interface ILoadingView {

    RelativeLayout init();


    void setView(String text, String drawable, View.OnClickListener listener);

}
