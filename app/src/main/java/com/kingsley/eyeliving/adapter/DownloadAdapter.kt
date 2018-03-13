package com.kingsley.eyeliving.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kingsley.eyeliving.R
import com.kingsley.eyeliving.mvp.model.bean.VideoBean
import com.kingsley.eyeliving.ui.VideoDetailActivity
import com.kingsley.eyeliving.utils.ImageLoadUtils
import com.kingsley.eyeliving.utils.SPUtils
import io.reactivex.disposables.Disposable
import zlc.season.rxdownload2.RxDownload
import zlc.season.rxdownload2.entity.DownloadFlag

/**
 * Created by Stephen on 2018/3/13.
 */
class DownloadAdapter(context: Context, list: ArrayList<VideoBean>) : RecyclerView.Adapter<DownloadAdapter.DownloadViewHolder>() {

    lateinit var mOnLongListener: OnLongClickListener
    var context: Context? = null
    var list: ArrayList<VideoBean>? = null
    var inflater: LayoutInflater? = null
    var isDownload = false
    var hasLoaded = false
    lateinit var disposable: Disposable

    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DownloadViewHolder {
        return DownloadViewHolder(inflater?.inflate(R.layout.item_download, parent, false), context!!)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: DownloadViewHolder?, position: Int) {
        var photoUrl: String? = list?.get(position)?.feed
        photoUrl?.let { ImageLoadUtils.display(context!!, holder?.iv_photo, it) }
        var title: String? = list?.get(position)?.title
        holder?.tv_title?.text = title
        var category = list?.get(position)?.category
        var duration = list?.get(position)?.duration
        isDownload = SPUtils.getInstance(context!!, "download_state").getBoolean(list?.get(position)?.playUrl!!)
        getDownloadState(list?.get(position)?.playUrl, holder)
        if (isDownload) {
            holder?.iv_download_state?.setImageResource(R.drawable.icon_download_stop)
        } else {
            holder?.iv_download_state?.setImageResource(R.drawable.icon_download_start)
        }
        holder?.itemView?.setOnClickListener {
            //跳转视频详情页
            var intent: Intent = Intent(context, VideoDetailActivity::class.java)
            var desc = list?.get(position)?.description
            var playUrl = list?.get(position)?.playUrl
            var blurred = list?.get(position)?.blurred
            var collect = list?.get(position)?.collect
            var share = list?.get(position)?.share
            var reply = list?.get(position)?.reply
            var time = System.currentTimeMillis()
            var videoBean = VideoBean(photoUrl, title, desc, duration, playUrl, category, blurred, collect, share, reply, time)
            var url = SPUtils.getInstance(context!!, "beans").getString(playUrl!!)
            intent.putExtra("data", videoBean as Parcelable)
            if (hasLoaded) {
                var files = RxDownload.getInstance(context).getRealFiles(playUrl)
                var uri = Uri.fromFile(files!![0])
                intent.putExtra("loaclFile", uri.toString())
            }

            context?.let { context -> context.startActivity(intent) }
        }
        holder?.itemView?.setOnLongClickListener {
            mOnLongListener.onLongClick(position)
            true
        }



    }

    private fun getDownloadState(playUrl: String?, holder: DownloadViewHolder?) {
        disposable = RxDownload.getInstance(context).receiveDownloadStatus(playUrl)
                .subscribe { event ->
                    // 当事件为Failed时，才会有异常信息
                    if (event.flag == DownloadFlag.FAILED) {
                        val throwable = event.error
                    }
                    var downloadStatus = event.downloadStatus
                    var percent = downloadStatus.percentNumber
                    if (percent == 100L) {
                        if (!disposable.isDisposed && disposable != null) {
                            disposable.dispose()
                        }
                        hasLoaded = true
                        holder?.iv_download_state?.visibility = View.GONE
                        holder?.tv_detail?.text = "已缓存"
                        isDownload = false
                        SPUtils.getInstance(context!!, "download_state").put(playUrl.toString(), false)
                    } else {
                        if (holder?.iv_download_state?.visibility != View.VISIBLE) {
                            holder?.iv_download_state?.visibility = View.VISIBLE
                        }
                        if (isDownload) {
                            holder?.tv_detail?.text = "缓存中 / $percent%"
                        } else {
                            holder?.tv_detail?.text = "已暂停 / $percent%"
                        }
                    }
                }
    }

    class DownloadViewHolder(itemView: View?, context: Context) : RecyclerView.ViewHolder(itemView) {
        var iv_photo: ImageView? = itemView?.findViewById(R.id.iv_photo)
        var tv_title: TextView? = itemView?.findViewById(R.id.tv_title)
        var tv_detail: TextView? = itemView?.findViewById(R.id.tv_detail)
        var iv_download_state: ImageView? = itemView?.findViewById(R.id.iv_download_state)

        init {
            tv_title?.typeface = Typeface.createFromAsset(context?.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
        }
    }

    fun setOnLongClickListener(OnLongClickListener: OnLongClickListener) {
        mOnLongListener = OnLongClickListener
    }


    interface OnLongClickListener {
        fun onLongClick(position: Int)
    }

}