package com.kingsley.eyeliving.mvp.model

import android.content.Context
import com.kingsley.eyeliving.mvp.model.bean.HotBean
import com.kingsley.eyeliving.network.ApiService
import com.kingsley.eyeliving.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by Stephen on 2018/3/12.
 */
class FeedDetailModel {
    fun loadData(context: Context, categoryName: String, strategy: String?): Observable<HotBean>? {
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService = retrofitClient.create(ApiService::class.java)
        return apiService?.getFindDetailData(categoryName, strategy!!, "26868b32e808498db32fd51fb422d00175e179df", 83)
    }

    fun loadMoreData(context: Context, start: Int, categoryName: String, strategy: String?): Observable<HotBean>? {
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService = retrofitClient.create(ApiService::class.java)
        return apiService?.getFindDetailMoreData(start, 10, categoryName, strategy!!)
    }
}