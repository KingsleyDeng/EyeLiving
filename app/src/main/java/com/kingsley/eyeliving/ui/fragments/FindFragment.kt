package com.kingsley.eyeliving.ui.fragments

import com.kingsley.eyeliving.R
import com.kingsley.eyeliving.adapter.FindAdapter
import com.kingsley.eyeliving.mvp.contract.FindContract
import com.kingsley.eyeliving.mvp.model.bean.FindBean
import com.kingsley.eyeliving.mvp.presenter.FindPresenter
import kotlinx.android.synthetic.main.find_fragment.*

/**
 * Created by Stephen on 2018/3/5.
 * 发现页面
 */
class FindFragment : BaseFragment(), FindContract.View {

    var mPresenter: FindPresenter? = null
    var mList: MutableList<FindBean>? = null
    var mAdapter: FindAdapter? = null

    override fun setData(beans: MutableList<FindBean>) {
        mAdapter?.mList = beans
        mList = beans
        mAdapter?.notifyDataSetChanged()
    }

    override fun initView() {
        mPresenter = FindPresenter(context,this)
        mPresenter?.start()
        mAdapter = FindAdapter(context,mList)
        gv_find.adapter = mAdapter
    }

    override fun getLayoutResources(): Int {
        return R.layout.find_fragment
    }
}