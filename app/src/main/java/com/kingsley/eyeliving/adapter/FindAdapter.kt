package com.kingsley.eyeliving.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.kingsley.eyeliving.R
import com.kingsley.eyeliving.mvp.model.bean.FindBean
import com.kingsley.eyeliving.utils.ImageLoadUtils

/**
 * Created by Stephen on 2018/3/6.
 */
class FindAdapter(context: Context, list: MutableList<FindBean>?) : BaseAdapter() {

    var mContext: Context? = null
    var mList: MutableList<FindBean>? = null
    var mInflater: LayoutInflater? = null

    init {
        mContext = context
        mList = list
        mInflater = LayoutInflater.from(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View
        var holder: FindViewHolder
        if (convertView == null) {
            view = mInflater!!.inflate(R.layout.item_find, parent, false)
            holder = FindViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as FindViewHolder
        }
        ImageLoadUtils.display(mContext!!, holder.iv_photo, mList?.get(position)?.bgPicture!!)
        holder.tv_title?.text = mList?.get(position)!!.name
        return view
    }

    override fun getItem(p0: Int): FindBean? {
        return mList?.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        if (mList != null) {
            return mList!!.size
        } else {
            return 0
        }
    }

    class FindViewHolder(itemView: View) {
        var iv_photo: ImageView? = null
        var tv_title: TextView? = null

        init {
            tv_title = itemView.findViewById(R.id.tv_title)
            iv_photo = itemView.findViewById(R.id.iv_photo)

        }

    }

}