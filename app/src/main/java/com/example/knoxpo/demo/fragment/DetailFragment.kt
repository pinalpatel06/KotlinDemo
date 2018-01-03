package com.example.knoxpo.demo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.knoxpo.demo.R
import com.example.knoxpo.demo.data.model.Post

/**
 * Created by knoxpo on 25/12/17.
 */
class DetailFragment : Fragment() {

    companion object {
        private val ARGS_TITLE: String = "DetailFragment.ARGS_TITLE"

        fun newInstance(post: Post): DetailFragment{
            val args = Bundle()
            val fragment = DetailFragment()
            args.putParcelable(DetailFragment.ARGS_TITLE,post)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_detail, container,false)

        val args: Bundle = arguments
        val textTV = view.findViewById<TextView>(R.id.tv_detail_title)

        val post: Post = args.getParcelable(ARGS_TITLE)
        if (post.getPostBody().isNotEmpty()){
            textTV.text = post.getPostBody()
        }

        return  view
    }
}