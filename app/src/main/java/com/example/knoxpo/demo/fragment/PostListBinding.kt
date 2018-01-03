package com.example.knoxpo.demo.fragment

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.example.knoxpo.demo.data.model.Post

/**
 * Created by knoxpo on 3/1/18.
 */
object PostListBinding {
    @BindingAdapter("app:items")
    @JvmStatic fun setItems(recyclerView: RecyclerView, items: List<Post>){
        with(recyclerView.adapter as PostAdapter ){
            replaceData(items)
        }
    }
}