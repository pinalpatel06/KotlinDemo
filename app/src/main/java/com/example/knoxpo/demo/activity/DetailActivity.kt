package com.example.knoxpo.demo.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.example.knoxpo.demo.fragment.DetailFragment

/**
 * Created by knoxpo on 25/12/17.
 */
class DetailActivity : ToolbarActivity() {
    val EXTRA_TITLE: String = "DetailActivity.EXTRA_TITLE"

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home){
          super.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setTitle("Detail")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun getContentFragment(): Fragment {
        val str: String = intent.getStringExtra(EXTRA_TITLE)
        return DetailFragment.newInstance(str)
    }
}