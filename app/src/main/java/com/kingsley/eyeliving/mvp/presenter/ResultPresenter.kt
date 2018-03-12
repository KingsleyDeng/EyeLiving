package com.kingsley.eyeliving.mvp.presenter

import android.content.Context
import com.kingsley.eyeliving.mvp.contract.ResultContract
import com.kingsley.eyeliving.mvp.model.ResultModel
import com.kingsley.eyeliving.mvp.model.bean.HotBean
import com.kingsley.eyeliving.utils.applySchedulers
import io.reactivex.Observable

/**
 * Created by Stephen on 2018/3/12.
 */
class ResultPresenter(context: Context, view: ResultContract.View) : ResultContract.Presenter {

    var mContext: Context? = null
    var mView: ResultContract.View? = null
    val mModel: ResultModel by lazy {
        ResultModel()
    }

    init {
        mView = view
        mContext = context
    }

    override fun start() {

    }

    override fun requestData(query: String, start: Int) {
        val observable: Observable<HotBean>? = mContext?.let { mModel.loadData(mContext!!, query, start) }
        observable?.applySchedulers()?.subscribe { bean: HotBean ->
            mView?.setData(bean)
        }
    }

}