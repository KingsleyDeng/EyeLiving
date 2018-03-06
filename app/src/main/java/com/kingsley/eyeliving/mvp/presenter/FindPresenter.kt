package com.kingsley.eyeliving.mvp.presenter

import android.content.Context
import com.kingsley.eyeliving.mvp.contract.FindContract
import com.kingsley.eyeliving.mvp.model.FindModel
import com.kingsley.eyeliving.mvp.model.bean.FindBean
import com.kingsley.eyeliving.utils.applySchedulers
import io.reactivex.Observable

/**
 * Created by Stephen on 2018/3/6.
 */
class FindPresenter(context: Context, view: FindContract.View) : FindContract.Presenter {

    var mContext: Context? = null
    var mView: FindContract.View? = null
    val mModel: FindModel by lazy {
        FindModel()
    }

    init {
        mContext = context
        mView = view
    }

    override fun start() {
        requestData()
    }

    override fun requestData() {
        val observable: Observable<MutableList<FindBean>>? = mContext?.let { mModel.loadData(mContext!!) }
        observable?.applySchedulers()?.subscribe { beans: MutableList<FindBean> ->
            mView?.setData(beans)
        }
    }

}