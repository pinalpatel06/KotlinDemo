package com.example.knoxpo.demo.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.example.knoxpo.demo.fragment.DetailFragment
import com.example.knoxpo.demo.data.model.Post

/**
 * Created by knoxpo on 25/12/17.
 */
class DetailActivity : ToolbarActivity() {
    companion object {
        val EXTRA_TITLE: String = "DetailActivity.EXTRA_TITLE"
    }

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
        val post: Post = intent.getParcelableExtra(EXTRA_TITLE)
        return DetailFragment.newInstance(post)
    }
}