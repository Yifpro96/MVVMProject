package com.aoxing.mymvvm.ui.square

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import cn.bingoogolapple.bgabanner.BGABanner
import coil.load
import com.aoxing.mymvvm.R
import com.aoxing.mymvvm.common.BaseFragment
import com.aoxing.mymvvm.common.alert
import com.aoxing.mymvvm.databinding.FragmentSquareBinding
import com.aoxing.mymvvm.model.HomeArticle
import com.aoxing.mymvvm.noMoreData
import com.orhanobut.logger.Logger
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.header_square_banner.*
import org.koin.android.ext.android.inject

class SquareFragment : BaseFragment(R.layout.fragment_square) {

    companion object {
        fun getInstance(): SquareFragment = SquareFragment()
    }

    private val mBinding by binding<FragmentSquareBinding>()
    private val mViewModel by inject<SquareViewModel>()
    private val mBannerAdapter by lazy {
        BGABanner.Adapter<ImageView, String> { _, itemView, model, _ ->
            itemView.load(model) {
                crossfade(true)
            }
        }
    }
    private val mSquareAdapter by lazy { SquareAdapter() }
    private var mCurPage = 0
    private lateinit var mBanner: BGABanner

    override fun lazyLoad() {
        mBinding.run {
            refreshLayout.run {
                setOnRefreshListener(mOnRefreshListener)
                setOnLoadMoreListener(mOnLoadMoreListener)
            }
            lifecycleOwner = this@SquareFragment
            recyclerView.adapter = mSquareAdapter.apply {
                headerView = View.inflate(context, R.layout.header_square_banner, null).apply {
                    mBanner=findViewById(R.id.banner)
                }
            }
            viewModel = mViewModel.apply {
                failure.observe(viewLifecycleOwner) {
                    alert(activity, it)
                }
                fetchData()
            }
        }

    }

    private fun fetchData() {
        mViewModel.run {
            fetchBanner().observe(viewLifecycleOwner) {
                val models = arrayListOf<String>()
                val tips = arrayListOf<String>()
                it.forEach { item ->
                    models.add(item.imagePath)
                    tips.add(item.title)
                }
                mBanner.run {
                    setAutoPlayAble(models.size > 1)
                    setData(models, tips)
                    setAdapter(mBannerAdapter)
                }
            }
            fetchSquares().observe(viewLifecycleOwner) {
                it.datas.add(0, HomeArticle.Data())
                mSquareAdapter.submitList(it.datas, SubmitListCallback(recyclerView))
                refreshLayout.setNoMoreData(it.curPage + 1 == it.pageCount)
            }
        }
    }

    class SubmitListCallback(val recyclerView: RecyclerView) : Runnable {
        override fun run() {
            recyclerView.scrollToPosition(0)
            recyclerView.requestFocus()
        }
    }

    private val mOnRefreshListener = OnRefreshListener {
        fetchData()
    }

    private val mOnLoadMoreListener = OnLoadMoreListener {
        mViewModel.fetchSquares(++mCurPage).observe(viewLifecycleOwner) {
            mSquareAdapter.submitList(mSquareAdapter.currentList + it.datas)
            refreshLayout.noMoreData = it.curPage + 1 == it.pageCount
        }
    }
}