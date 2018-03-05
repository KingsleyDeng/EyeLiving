package com.kingsley.eyeliving.ui.fragments

import android.support.v4.widget.SwipeRefreshLayout
import com.kingsley.eyeliving.R
import com.kingsley.eyeliving.mvp.contract.HomeContract
import com.kingsley.eyeliving.mvp.model.bean.HomeBean
import com.kingsley.eyeliving.mvp.presenter.HomePresenter

/**
 * Created by Stephen on 2018/3/5.
 */
class HomeFragment : BaseFragment(), HomeContract.View,SwipeRefreshLayout.OnRefreshListener {

    var mIsRefresh: Boolean = false
    var mPresenter: HomePresenter? = null

    override fun setData(bean: HomeBean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRefresh() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutResources(): Int {
        return R.layout.home_fragment
    }

    override fun initView() {
        mPresenter = HomePresenter(context,this)

    }
}