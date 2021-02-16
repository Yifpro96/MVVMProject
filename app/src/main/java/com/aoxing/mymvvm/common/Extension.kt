package com.aoxing.mymvvm.common

import android.content.Context
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

fun alert(context: Context?, message: String?) {
    context?.alert(message ?: ""){
        yesButton {  }
    }?.show()
}

fun getError(state: CombinedLoadStates): LoadState.Error?{
    return when {
        state.prepend is LoadState.Error -> state.prepend as LoadState.Error
        state.append is LoadState.Error -> state.append as LoadState.Error
        state.refresh is LoadState.Error -> state.refresh as LoadState.Error
        else -> null
    }
}

fun dp2px(dpValue: Float): Int {
    return dp2px(App.context, dpValue)
}

fun dp2px(context: Context, dpValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}