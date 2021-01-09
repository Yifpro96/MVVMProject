package com.aoxing.mymvvm

import android.content.Context

fun dp2px(dpValue: Float): Int {
    return dp2px(YifApp.context, dpValue)
}

fun dp2px(context: Context, dpValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}