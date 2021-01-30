package com.aoxing.mymvvm.common

import android.os.Bundle
import android.view.View
import com.hi.dhl.jdatabinding.DataBindingFragment

abstract class BaseFragment(layoutId: Int) : DataBindingFragment(contentLayoutId = layoutId) {

    private var isViewPrepare = false
    private var hasLoadData = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPrepare = true
        lazyLoadDataIfPrepared()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
        }
    }

    private fun lazyLoadDataIfPrepared() {
        if (userVisibleHint && isViewPrepare && !hasLoadData) {
            lazyLoad()
            hasLoadData = true
        }
    }

    abstract fun lazyLoad()
}