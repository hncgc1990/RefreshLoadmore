
分页加载的工具类,使用以下的工具进行组装下拉刷新加载更多,并对分页的逻辑以及对应的状态的显示的工具类

- 下拉刷新:[android-Ultra-Pull-To-Refresh](https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh)
- 状态切换:[ProgressLayout](https://github.com/nguyenhoanglam/ProgressLayout)
- listview的加载更多:[android-cube-app](https://github.com/liaohuqiu/android-cube-app)
- recyclerview的加载更多:[RecyclerViewAdapter](https://github.com/Othershe/RecyclerViewAdapter)



## 使用方法:

#### 1.使用ListView时,使用RefreshLoadMoreListHelper

在布局中添加：
```xml
<in.srain.cube.views.ptr.PtrClassicFrameLayout
        android:id="@+id/ptrFrameLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        <com.nguyenhoanglam.progresslayout.ProgressLayout
            android:id="@+id/progressLayout"
            android:layout_width="match_parent"
            android:layout_height="match_parent">
            <com.hncgc1990.library.loadMore.LoadMoreListViewContainer
                android:id="@+id/loadMoreContainer"
                android:layout_width="match_parent"
                android:layout_height="match_parent">
                <ListView
                    android:id="@+id/listview"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:divider="@null"
                    android:fadingEdge="none"
                    android:listSelector="@android:color/transparent"
                    android:paddingLeft="12dp"
                    android:paddingRight="12dp"
                    android:scrollbarStyle="outsideOverlay" />
            </com.hncgc1990.library.loadMore.LoadMoreListViewContainer>
        </com.nguyenhoanglam.progresslayout.ProgressLayout>
</in.srain.cube.views.ptr.PtrClassicFrameLayout>
```

代码中添加:
1.adapter要实现IAdapter接口 参考
2.初始化,设置刷新,加载更多事件触发的回调
```java
mHelper = new RefreshLoadMoreListHelper(this, ptrFrameLayout, progressLayout, loadMoreContainer, new RefreshLoadMoreListHelper.LoadDataListener() {
            @Override
            public void doLoadData(int page, int perPage) {
            	//调用数据请求
                getListData(page);
      }
});
```

3.刷新请求

```java
mHelper.refresh();
```


4.在请求的错误回调中调用:

```java
 mHelper.handlerError();
```

5.在请求的成功回调中调用:

```java
List<Result> results = value.getResults();
mHelper.handlerSuccess(myAdapter,results);

```


#### 2.使用RecyclerView时,使用RefreshLoadMoreRecyclerHelper
1.在布局中添加

```xml
<in.srain.cube.views.ptr.PtrClassicFrameLayout
        android:id="@+id/ptrFrameLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        <com.nguyenhoanglam.progresslayout.ProgressLayout
            android:id="@+id/progressLayout"
            android:layout_width="match_parent"
            android:layout_height="match_parent">
            <android.support.v7.widget.RecyclerView
                android:id="@+id/recyclerView"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:paddingLeft="12dp"
                android:paddingRight="12dp">
            </android.support.v7.widget.RecyclerView>
       </com.nguyenhoanglam.progresslayout.ProgressLayout>
</in.srain.cube.views.ptr.PtrClassicFrameLayout>
```

代码中添加:
1.adapter要实现IAdapter接口  且继承[CommonBaseAdapter](https://github.com/hncgc1990/RefreshLoadmore/blob/master/library/src/main/java/com/hncgc1990/library/recyclerLoadMore/base/CommonBaseAdapter.java)类 参考[RecyclerAdapter ](https://github.com/hncgc1990/RefreshLoadmore/blob/master/app/src/main/java/com/hncgc1990/refreshloadmore/adapter/RecyclerAdapter.java)
2.初始化,设置刷新,加载更多事件触发的回调
```java
mHelper=new RefreshLoadMoreRecyclerHelper(this, ptrFrameLayout, progressLayout, mAdapter, new RefreshLoadMoreRecyclerHelper.LoadDataListener() {
            @Override
            public void doLoadData(int page, int perPage) {
                getListData(page);
            }
        });
```

3.刷新请求

```java
mHelper.refresh();
```


4.在请求的错误回调中调用:

```java
 mHelper.handlerError();
```

5.在请求的成功回调中调用:

```java
List<Result> results = value.getResults();
mHelper.handlerSuccess(myAdapter,results);

```

更多详细的使用参考demo

#### 3.自定义加载更多布局
实现LoadMoreUIHandler接口,调用对应的Helper设置即可,参考[MyFooterView](https://github.com/hncgc1990/RefreshLoadmore/blob/master/app/src/main/java/com/hncgc1990/refreshloadmore/view/MyFooterView.java)
```java
 mHelper.setLoadMoreUIHandler(new MyFooterView(this));
```

#### 4.自定义下拉刷新布局
参考[android-Ultra-Pull-To-Refresh](https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh)


#### 5.自定义状态切换
- 自定义加载状态view实现 ILoadingView 接口
- 自定义空数据状态view实现 IEmptyView 接口
- 自定义错误状态viw实现 IErrorView 接口

然后调用progresslayout的方法进行设置,如:

```java
//自定义空布局
progressLayout.setEmptyView(new MyEmptyView(getLayoutInflater()));
```


## 感谢
- [干货集中营](http://gank.io/history)提供的api
- [android-Ultra-Pull-To-Refresh](https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh)
- [ProgressLayout](https://github.com/nguyenhoanglam/ProgressLayout)
- [android-cube-app](https://github.com/liaohuqiu/android-cube-app)
- [RecyclerViewAdapter](https://github.com/Othershe/RecyclerViewAdapter)















