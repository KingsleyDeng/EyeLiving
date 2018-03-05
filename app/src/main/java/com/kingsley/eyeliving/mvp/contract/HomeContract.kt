package com.kingsley.eyeliving.mvp.contract

import com.kingsley.eyeliving.base.BasePresenter
import com.kingsley.eyeliving.base.BaseView
import com.kingsley.eyeliving.mvp.model.bean.HomeBean

/**
 * Created by Stephen on 2018/3/5.
 */
interface HomeContract {

    interface View : BaseView<Presenter> {
        fun setData(bean: HomeBean)
    }

    interface Presenter : BasePresenter {
        fun requestData()
    }

}