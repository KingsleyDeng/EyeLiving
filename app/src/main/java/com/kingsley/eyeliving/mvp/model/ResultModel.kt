package com.kingsley.eyeliving.mvp.model

import android.content.Context
import com.kingsley.eyeliving.mvp.model.bean.HotBean
import com.kingsley.eyeliving.network.ApiService
import com.kingsley.eyeliving.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by Stephen on 2018/3/12.
 */
class ResultModel {

    fun loadData(context: Context, query: String, start: Int): Observable<HotBean>? {
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService = retrofitClient.create(ApiService::class.java)
        return apiService?.getSearchData(10, query, start)
    }

}