package com.aoxing.mymvvm.ui

import com.aoxing.mymvvm.MainAdapter
import com.aoxing.mymvvm.MainViewModel
import com.aoxing.mymvvm.R
import com.aoxing.mymvvm.databinding.FragmentHomeBinding
import com.orhanobut.logger.Logger
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val mBinding by binding<FragmentHomeBinding>()
    private val mViewModel by inject<MainViewModel>()
    private val mMainAdapter by lazy { MainAdapter() }

    override fun lazyLoad() {
        mBinding.run {
            recyclerView.adapter = mMainAdapter
            mainViewModel = mViewModel
            lifecycleOwner = this@HomeFragment
        }

        Logger.e("lazy load data........")
        mViewModel.fetchArticles().observe(viewLifecycleOwner) {
            mMainAdapter.submitData(lifecycle, it)
        }
    }
}