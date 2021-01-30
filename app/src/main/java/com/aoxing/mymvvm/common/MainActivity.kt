package com.aoxing.mymvvm.common

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.aoxing.mymvvm.R
import com.aoxing.mymvvm.ui.MyFragment
import com.aoxing.mymvvm.ui.SecondFragment
import com.aoxing.mymvvm.ui.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val Fragment_Home = 0
    private val Fragment_App = 1
    private val Fragment_Survey = 2

    private var mHomeFragment: HomeFragment? = null
    private var mSecondFragment: SecondFragment? = null
    private var mMyFragment: MyFragment? = null
    private var mIndex = 0

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = getColor(R.color.colorPrimaryDark)
        setContentView(R.layout.activity_main)
        showFragment(mIndex)
        navBottomBar.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.isChecked = true
        when (item.itemId) {
            R.id.page_1 -> showFragment(Fragment_Home)
            R.id.page_2 -> showFragment(Fragment_App)
            R.id.page_3 -> showFragment(Fragment_Survey)
        }
        return true
    }

    private fun showFragment(index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragment(transaction)
        mIndex = index
        when (index) {
            Fragment_Home -> {
                toolbar.title = "首页"
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.getInstance()
                    transaction.add(R.id.container, mHomeFragment!!, "")
                }else{
                    transaction.show(mHomeFragment!!)
                }
            }
            Fragment_App -> {
                toolbar.title = "广场"
                if (mSecondFragment == null) {
                    mSecondFragment = SecondFragment.getInstance()
                    transaction.add(R.id.container, mSecondFragment!!, "")
                }else{
                    transaction.show(mSecondFragment!!)
                }
            }
            Fragment_Survey -> {
                toolbar.title = "我的"
                if (mMyFragment == null) {
                    mMyFragment = MyFragment.getInstance()
                    transaction.add(R.id.container, mMyFragment!!, "")
                }else{
                    transaction.show(mMyFragment!!)
                }
            }
        }
        transaction.commit()
    }

    private fun hideFragment(transaction: FragmentTransaction) {
        val page = arrayOf(mHomeFragment, mSecondFragment, mMyFragment)
        page.forEach { p -> p?.let { transaction.hide(it) } }
    }
}