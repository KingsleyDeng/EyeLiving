package com.kingsley.eyeliving.mvp.model

import android.content.Context
import com.kingsley.eyeliving.mvp.model.bean.HotBean
import com.kingsley.eyeliving.network.ApiService
import com.kingsley.eyeliving.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by lvruheng on 2017/7/7.
 */
class HotModel {
    fun loadData(context: Context, strategy: String?): Observable<HotBean>? {
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService = retrofitClient.create(ApiService::class.java)
        return apiService?.getHotData(10, strategy!!, "26868b32e808498db32fd51fb422d00175e179df", 83)

    }
}