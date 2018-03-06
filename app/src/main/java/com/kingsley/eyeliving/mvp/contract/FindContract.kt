package com.kingsley.eyeliving.mvp.contract

import com.kingsley.eyeliving.base.BasePresenter
import com.kingsley.eyeliving.base.BaseView
import com.kingsley.eyeliving.mvp.model.bean.FindBean

/**
 * Created by Stephen on 2018/3/6.
 */
interface FindContract {

    interface View : BaseView<Presenter>{
        fun setData(beans : MutableList<FindBean>)
    }

    interface Presenter : BasePresenter{
        fun requestData()
    }

}