package com.kingsley.eyeliving.mvp.model

import android.content.Context
import com.kingsley.eyeliving.mvp.model.bean.FindBean
import com.kingsley.eyeliving.network.ApiService
import com.kingsley.eyeliving.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by Stephen on 2018/3/6.
 */
class FindModel {
    fun loadData(context: Context): Observable<MutableList<FindBean>>? {
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService = retrofitClient.create(ApiService::class.java)
        return apiService?.getFindData()
    }
}