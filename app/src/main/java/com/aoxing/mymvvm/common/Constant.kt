package com.aoxing.mymvvm.common

import androidx.paging.PagingConfig

const val Default_Page_Index = 0
const val Default_Page_Size = 20
val Rounded_Corner = dp2px(4f)
val pageConfig = PagingConfig(
    pageSize = Default_Page_Size, //每页条目数量
    enablePlaceholders = true, //条目占位
    prefetchDistance = 3, //预加载距离
    initialLoadSize = 10 //初始化条目数量
)