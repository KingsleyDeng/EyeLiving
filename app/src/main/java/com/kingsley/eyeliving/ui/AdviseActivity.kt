package com.kingsley.eyeliving.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gyf.barlibrary.ImmersionBar
import com.kingsley.eyeliving.R
import kotlinx.android.synthetic.main.activity_advise.*

class AdviseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advise)
        ImmersionBar.with(this).transparentBar().barAlpha(0.3f).fitsSystemWindows(true).init()
        settoolbar()
    }

    fun settoolbar() {
        setSupportActionBar(toolbar)
        var bar = supportActionBar
        bar?.title = "意见反馈"
        bar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

}
