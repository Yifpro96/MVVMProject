package com.aoxing.erpproject.common.gson

class StringResult {

    var IsSucceed: Boolean = false
    var Message: String = ""
    var StatusCode: Int = 0
    var Entity: String = ""

    fun toYifproResult(): YifproResult<String> {
        val result = YifproResult<String>()
        result.IsSucceed = IsSucceed
        result.Message = Message
        result.StatusCode = StatusCode
        result.Entity = Entity
        return result
    }
}