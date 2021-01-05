package com.aoxing.mymvvm.model

data class YifproResult<T>(
    var data: T? = null,
    var errorCode: Int = 0,
    var errorMsg: String = ""
)