package com.aoxing.mymvvm.ui.home

import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.aoxing.mymvvm.R
import com.aoxing.mymvvm.common.BaseFragment
import com.aoxing.mymvvm.common.alert
import com.aoxing.mymvvm.common.footer.FooterAdapter
import com.aoxing.mymvvm.common.getError
import com.aoxing.mymvvm.databinding.FragmentHomeBinding
import com.aoxing.mymvvm.refresh
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    companion object {
        fun getInstance(): HomeFragment = HomeFragment()
    }

    private val mBinding by binding<FragmentHomeBinding>()
    private val mViewModel by inject<HomeViewModel>()
    private val mHomeAdapter by lazy { HomeAdapter() }

    override fun lazyLoad() {
        mBinding.run {
            refreshLayout.setOnRefreshListener(mOnRefreshListener)
            lifecycleOwner = this@HomeFragment
            recyclerView.adapter = mHomeAdapter.withLoadStateFooter(FooterAdapter(mHomeAdapter))
            viewModel = mViewModel.apply {
                fetchData()
            }
        }

        lifecycleScope.launchWhenCreated {
            mHomeAdapter.loadStateFlow.collectLatest { state ->
                mBinding.refreshLayout.refresh = state.refresh is LoadState.Loading
                val error = getError(state)
                error?.let { alert(activity, it.error.message) }
            }
        }
    }

    private fun fetchData() {
        mViewModel.run {
            fetchHomeData().observe(viewLifecycleOwner) {
                mHomeAdapter.submitData(lifecycle, it)
            }
        }
    }

    private val mOnRefreshListener = OnRefreshListener {
        mHomeAdapter.submitData(lifecycle, PagingData.empty())
        fetchData()
    }
}