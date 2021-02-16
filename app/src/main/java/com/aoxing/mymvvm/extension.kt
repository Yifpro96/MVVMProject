package com.aoxing.mymvvm

import com.scwang.smart.refresh.layout.SmartRefreshLayout

var SmartRefreshLayout.refresh: Boolean
    set(value) {
        if (value) autoRefresh() else finishRefresh()
    }
    get() = isRefreshing

var SmartRefreshLayout.noMoreData: Boolean
    set(value) {
        if (value) finishLoadMoreWithNoMoreData() else finishLoadMore()
    }
    get() = false