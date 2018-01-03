package com.example.knoxpo.demo.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.knoxpo.demo.fragment.MainFragment
import com.example.knoxpo.demo.fragment.PostItemNavigator
import com.example.knoxpo.demo.fragment.PostViewModel
import com.example.knoxpo.demo.utils.obtainViewModel

class MainActivity : ToolbarActivity(), PostItemNavigator {
    override fun openPostDetail(postId: Long) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_TITLE, postId)
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
           // openPostDetail()
        }

    }

    fun obtainViewModel(): PostViewModel= obtainViewModel(PostViewModel::class.java)
}
