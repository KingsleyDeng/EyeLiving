package com.kingsley.eyeliving.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Stephen on 2018/3/5.
 */
abstract class BaseFragment : Fragment() {

    var isFirst: Boolean = false

    var rootView: View? = null

    var isFragmentVisiable: Boolean = false

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView != null) {
            rootView = inflater?.inflate(getLayoutResources(), container, false)
        }
        return rootView
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (isVisibleToUser) {
            isFragmentVisiable = true
        }

        if (rootView == null) {
            return
        }

        //可见 并且没有加载过
        if (!isFirst && isFragmentVisiable) {
            onFragmentVisiableChange(true)
            return
        }

        // 可见->不可见 已经加载过
        if (isFragmentVisiable) {
            onFragmentVisiableChange(false)
            isFragmentVisiable = false
        }

    }

    open protected fun onFragmentVisiableChange(b: Boolean) {

    }

    abstract fun getLayoutResources(): Int

    abstract fun initView()
}