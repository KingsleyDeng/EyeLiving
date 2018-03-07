package com.kingsley.eyeliving.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by Stephen on 2018/3/7.
 */
class HotAdapter(fm: FragmentManager, list: ArrayList<Fragment>, titles: MutableList<String>) : FragmentPagerAdapter(fm) {

    var mFm: FragmentManager = fm!!
    var mList: ArrayList<Fragment> = list
    var mTitles: MutableList<String> = titles

    override fun getItem(position: Int): Fragment {
        return mList[position]
    }

    override fun getCount(): Int {
        return mList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mTitles[position]
    }

}