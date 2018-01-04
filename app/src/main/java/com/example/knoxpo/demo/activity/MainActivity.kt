package com.example.knoxpo.demo.activity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.knoxpo.demo.data.model.Post
import com.example.knoxpo.demo.fragment.MainFragment
import com.example.knoxpo.demo.fragment.PostItemNavigator
import com.example.knoxpo.demo.fragment.PostViewModel
import com.example.knoxpo.demo.utils.obtainViewModel

class MainActivity : ToolbarActivity(), PostItemNavigator {

    override fun openPostDetail(post: Post) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_TITLE, post)
        }

        startActivity(intent)
    }

    lateinit var mViewModel: PostViewModel

    override fun getContentFragment(): Fragment {
        val fragment = MainFragment()
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = obtainViewModel().apply {
            openPostEvent.observe(this@MainActivity, Observer<Post> { t: Post? ->
                if(t != null){
                    openPostDetail(t)
                }
            })
        }
    }

    fun obtainViewModel(): PostViewModel= obtainViewModel(PostViewModel::class.java)
}
