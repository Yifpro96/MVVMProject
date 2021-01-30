package com.aoxing.mymvvm.ui.home

import android.view.View
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.bingoogolapple.bgabanner.BGABanner
import coil.load
import com.aoxing.mymvvm.R
import com.aoxing.mymvvm.common.BaseFragment
import com.aoxing.mymvvm.common.alert
import com.aoxing.mymvvm.common.footer.FooterAdapter
import com.aoxing.mymvvm.common.getError
import com.aoxing.mymvvm.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    companion object {
        fun getInstance(): HomeFragment = HomeFragment()
    }

    private val mBinding by binding<FragmentHomeBinding>()
    private val mViewModel by inject<HomeViewModel>()
    private val mHomeAdapter by lazy { HomeAdapter() }
    /*private val mBannerAdapter by lazy {
        BGABanner.Adapter<ImageView, String> { _, itemView, model, _ ->
            itemView.load(model) {
                crossfade(true)
            }
        }
    }*/

    override fun lazyLoad() {
        mBinding.run {
            swipeRefresh.setOnRefreshListener(mOnRefreshListener)
            lifecycleOwner = this@HomeFragment
            recyclerView.adapter = mHomeAdapter.withLoadStateFooter(FooterAdapter(mHomeAdapter))
            viewModel = mViewModel.apply {
                failure.observe(viewLifecycleOwner) {
                    alert(activity, it)
                }
                fetchData()
            }
        }

        lifecycleScope.launchWhenCreated {
            mHomeAdapter.loadStateFlow.collectLatest { state ->
                mBinding.swipeRefresh.isRefreshing = state.refresh is LoadState.Loading
                val error = getError(state)
                error?.let { alert(activity, it.error.message) }
            }
        }
    }

    private fun fetchData() {
        mViewModel.run {
            /*fetchBanner().observe(viewLifecycleOwner) {
                val models = arrayListOf<String>()
                val tips = arrayListOf<String>()
                it.forEach { item ->
                    models.add(item.imagePath)
                    tips.add(item.title)
                }
                banner.run {
                    setAutoPlayAble(models.size > 1)
                    setData(models, tips)
                    setAdapter(mBannerAdapter)
                }
            }*/
            fetchHomeData().observe(viewLifecycleOwner) {
                mHomeAdapter.submitData(lifecycle, it)
            }
        }
    }

    private val mOnRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        mHomeAdapter.submitData(lifecycle, PagingData.empty())
        fetchData()
    }
}