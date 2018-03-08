package com.kingsley.eyeliving.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import com.kingsley.eyeliving.R
import com.kingsley.eyeliving.adapter.HotAdapter
import kotlinx.android.synthetic.main.hot_fragment.*

/**
 * Created by Stephen on 2018/3/5.
 */
class HotFragment : BaseFragment() {

    var mTabs = listOf<String>("周排行","月排行","总排行").toMutableList()
    lateinit var mFragments: ArrayList<Fragment>
    val STARTEGY = arrayOf("weekly","monthly","historical")

    override fun getLayoutResources(): Int {
        return R.layout.hot_fragment
    }

    override fun initView() {

        var weekFragment : RankFragment = RankFragment()
        var weekBundle = Bundle()
        weekBundle.putString("strategy", STARTEGY[0])
        weekFragment.arguments = weekBundle
        var monthFragment: RankFragment = RankFragment()
        var monthBundle = Bundle()
        monthBundle.putString("strategy", STARTEGY[1])
        monthFragment.arguments = monthBundle
        var allFragment: RankFragment = RankFragment()
        var allBundle = Bundle()
        allBundle.putString("strategy", STARTEGY[2])
        allFragment.arguments = allBundle
        mFragments = ArrayList()
        mFragments.add(weekFragment as Fragment)
        mFragments.add(monthFragment as Fragment)
        mFragments.add(allFragment as Fragment)
        vp_content.adapter = HotAdapter(childFragmentManager, mFragments, mTabs)
        tabs.setupWithViewPager(vp_content)

    }

}