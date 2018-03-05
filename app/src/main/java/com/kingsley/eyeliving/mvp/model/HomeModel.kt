package com.kingsley.eyeliving.mvp.model

import android.content.Context
import com.kingsley.eyeliving.mvp.model.bean.HomeBean
import com.kingsley.eyeliving.network.ApiService
import com.kingsley.eyeliving.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by Stephen on 2018/3/5.
 */
class HomeModel {

    fun loadData(context: Context, isFirst: Boolean, data: String?): Observable<HomeBean>? {
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService  = retrofitClient.create(ApiService::class.java)
        when(isFirst) {
            true -> return apiService?.getHomeData()

            false -> return apiService?.getHomeMoreData(data.toString(), "2")
        }
    }

}