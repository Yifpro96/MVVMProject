package com.aoxing.mymvvm.model

data class YifResp<T>(
    var data: T? = null,
    var errorCode: Int = 0,
    var errorMsg: String = ""
)

sealed class YifResult<out T> {
    data class Success<out T>(val value: T) : YifResult<T>()

    data class Failure(val throwable: Throwable?) : YifResult<Nothing>()
}
