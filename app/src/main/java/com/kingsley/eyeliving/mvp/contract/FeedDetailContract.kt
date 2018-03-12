package com.kingsley.eyeliving.mvp.contract

import com.kingsley.eyeliving.base.BasePresenter
import com.kingsley.eyeliving.base.BaseView
import com.kingsley.eyeliving.mvp.model.bean.HotBean

/**
 * Created by Stephen on 2018/3/12.
 */
interface FeedDetailContract {
    interface View : BaseView<Presenter> {
        fun setData(bean: HotBean)
    }

    interface Presenter : BasePresenter {
        fun requestData(categoryName: String, strategy: String)
    }
}