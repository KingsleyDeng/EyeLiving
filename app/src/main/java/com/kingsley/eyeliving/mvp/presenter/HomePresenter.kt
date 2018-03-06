package com.kingsley.eyeliving.mvp.presenter

import android.content.Context
import com.kingsley.eyeliving.mvp.contract.HomeContract
import com.kingsley.eyeliving.mvp.model.HomeModel
import com.kingsley.eyeliving.mvp.model.bean.HomeBean
import com.kingsley.eyeliving.utils.applySchedulers
import io.reactivex.Observable

/**
 * Created by Stephen on 2018/3/5.
 */
class HomePresenter(context: Context, view: HomeContract.View) : HomeContract.Presenter {

    var mContext: Context? = null
    var mView: HomeContract.View? = null
    val mModel: HomeModel by lazy {
        HomeModel()
    }

    init {
        mView = view
        mContext = context
    }

    fun moreData(data: String?) {
        val observable: Observable<HomeBean>? = mContext?.let { mModel.loadData(it, false, data) }
        observable?.applySchedulers()?.subscribe { homeBean: HomeBean ->
            mView?.setData(homeBean)
        }
    }

    override fun start() {
        requestData()
    }

    override fun requestData() {
        val observable: Observable<HomeBean>? = mContext?.let { mModel.loadData(it, true, "0") }
        observable?.applySchedulers()?.subscribe { homeBean: HomeBean ->
            mView?.setData(homeBean)
        }
    }
}