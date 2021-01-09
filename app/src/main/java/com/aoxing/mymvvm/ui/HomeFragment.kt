package com.aoxing.mymvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.aoxing.mymvvm.MainAdapter
import com.aoxing.mymvvm.MainViewModel
import com.aoxing.mymvvm.R
import com.aoxing.mymvvm.databinding.FragmentHomeBinding
import com.hi.dhl.jdatabinding.DataBindingFragment
import org.koin.android.ext.android.inject

class HomeFragment : DataBindingFragment(R.layout.fragment_home) {

    private val mBinding by binding<FragmentHomeBinding>()
    private val mViewModel by inject<MainViewModel>()
    private val mMainAdapter by lazy { MainAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.run {
            recyclerView.adapter = mMainAdapter
            mainViewModel = mViewModel
            lifecycleOwner = this@HomeFragment
        }
        mViewModel.fetchHomeArticle().observe(viewLifecycleOwner) {
            mMainAdapter.submitData(lifecycle, it)
        }
    }
}