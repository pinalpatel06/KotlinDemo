package com.example.knoxpo.demo.data

import com.example.knoxpo.demo.data.model.Post
import org.jetbrains.annotations.NotNull

/**
 * Created by knoxpo on 2/1/18.
 */
interface PostDataSource {

    interface LoadPostCallback{
        fun onPostLoaded(list: List<Post>)
        fun onDataNotAvailable()
    }

    /*interface LoadSinglePostCallback{
        fun onPostLoaded(post: Post)
        fun onDataNotAvailable()
    }*/

   // fun getPost(@NotNull callback: LoadSinglePostCallback)
    fun getAllPost(@NotNull callback: LoadPostCallback)
}