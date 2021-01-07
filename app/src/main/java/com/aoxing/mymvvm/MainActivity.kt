package com.aoxing.mymvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.aoxing.mymvvm.databinding.ActivityMainBinding
import com.orhanobut.logger.Logger
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val mViewModel: MainViewModel by inject()
    private val mMainAdapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.run {
            recyclerView.adapter = mMainAdapter
            mainViewModel = mViewModel
            lifecycleOwner = this@MainActivity
        }
        mViewModel.fetchHomeArticle().observe(this) {
            Logger.e("fetch data....")
            mMainAdapter.submitData(lifecycle, it)
        }
    }
}