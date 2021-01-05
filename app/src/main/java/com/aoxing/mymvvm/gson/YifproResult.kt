package com.aoxing.erpproject.common.gson

import com.google.gson.annotations.SerializedName

data class YifproResult<T>(
        @SerializedName(value = "Entity", alternate = ["entity"])
        var Entity: T? = null,
        @SerializedName(value = "IsSucceed", alternate = ["isSucceed"])
        var IsSucceed: Boolean = false,
        @SerializedName(value = "Message", alternate = ["message"])
        var Message: String = "",
        @SerializedName(value = "StatusCode", alternate = ["statusCode"])
        var StatusCode: Int = 0
)
