package com.example.knoxpo.demo.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.example.knoxpo.demo.R

/**
 * Created by knoxpo on 25/12/17.
 */
abstract class ToolbarActivity : SingleFragmentActivity() {
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(getToolbarId())
    }

    override protected fun getContentViewId() : Int{
        return R.layout.activity_toolbar
    }

    fun getToolbarId(): Toolbar{
        toolbar = findViewById(R.id.toolbar)
        return findViewById(R.id.toolbar)
    }
}