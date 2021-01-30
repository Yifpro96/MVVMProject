package com.aoxing.mymvvm.model

data class HttpResp<T>(
    var data: T? = null,
    var errorCode: Int = 0,
    var errorMsg: String = ""
)

sealed class YifResult<out T> {
    data class Success<out T>(val value: T) : YifResult<T>()

    data class Failure(val throwable: Throwable?) : YifResult<Nothing>()
}

inline fun <reified T> YifResult<T>.doSuccess(success: (T) -> Unit) {
    if (this is YifResult.Success) {
        success(value)
    }
}

inline fun <reified T> YifResult<T>.doFailure(failure: (Throwable?) -> Unit) {
    if (this is YifResult.Failure) {
        failure(throwable)
    }
}
