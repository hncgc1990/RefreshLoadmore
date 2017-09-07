package com.hncgc1990.library;

import java.util.List;

/**
 * Created by Administrator on 2017-8-31.
 */

public interface IAdapter<T>{

    void updateData(boolean isRefresh, List list);

    T getData();
}
