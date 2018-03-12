package com.kingsley.eyeliving.mvp.presenter

import android.content.Context
import com.kingsley.eyeliving.mvp.contract.FeedDetailContract
import com.kingsley.eyeliving.mvp.model.FeedDetailModel
import com.kingsley.eyeliving.mvp.model.bean.HotBean
import com.kingsley.eyeliving.utils.applySchedulers
import io.reactivex.Observable

/**
 * Created by Stephen on 2018/3/12.
 */
class FindDetailPresenter(context: Context, view: FeedDetailContract.View) : FeedDetailContract.Presenter {

    var mContext: Context? = null
    var mView: FeedDetailContract.View? = null
    val mModel: FeedDetailModel by lazy {
        FeedDetailModel()
    }

    init {
        mView = view
        mContext = context
    }

    override fun start() {
    }

    override fun requestData(categoryName: String, strategy: String) {
        val observable: Observable<HotBean>? = mContext?.let { mModel.loadData(mContext!!, categoryName, strategy) }
        observable?.applySchedulers()?.subscribe { bean: HotBean ->
            mView?.setData(bean)
        }
    }

    fun requesMoreData(start: Int, categoryName: String, strategy: String) {
        val observable: Observable<HotBean>? = mContext?.let { mModel.loadMoreData(mContext!!, start, categoryName, strategy) }
        observable?.applySchedulers()?.subscribe { bean: HotBean ->
            mView?.setData(bean)
        }
    }
}