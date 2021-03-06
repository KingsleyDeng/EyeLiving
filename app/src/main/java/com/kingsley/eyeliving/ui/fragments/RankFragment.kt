package com.kingsley.eyeliving.ui.fragments

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.kingsley.eyeliving.R
import com.kingsley.eyeliving.adapter.RankAdapter
import com.kingsley.eyeliving.mvp.contract.HotContract
import com.kingsley.eyeliving.mvp.model.bean.HotBean
import com.kingsley.eyeliving.mvp.presenter.HotPresenter
import kotlinx.android.synthetic.main.rank_fragment.*

class RankFragment : BaseFragment(), HotContract.View {

    lateinit var mPresenter: HotPresenter
    lateinit var mStrategy: String
    lateinit var mAdapter: RankAdapter

    var mList: ArrayList<HotBean.ItemListBean.DataBean> = ArrayList()

    override fun getLayoutResources(): Int {
        return R.layout.rank_fragment
    }

    override fun initView() {
        recyclersView.layoutManager = LinearLayoutManager(context)
        mAdapter = RankAdapter(context, mList)
        recyclersView.adapter = mAdapter
        Toast.makeText(context,"Eyeliving", Toast.LENGTH_LONG).show()
        if (arguments != null) {
            mStrategy = arguments.getString("strategy")
            mPresenter = HotPresenter(context, this)
            mPresenter.requestData(mStrategy)
        }

    }

    override fun setData(bean: HotBean) {
        Log.e("rank", bean.toString())
        if (mList.size > 0) {
            mList.clear()
        }
        bean.itemList?.forEach {
            it.data?.let { it1 -> mList.add(it1) }
        }
        mAdapter.notifyDataSetChanged()
    }

}