package com.example.knoxpo.demo.fragment

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.example.knoxpo.demo.data.PostDataSource
import com.example.knoxpo.demo.data.PostRepository
import com.example.knoxpo.demo.data.model.Post


/**
 * Created by knoxpo on 2/1/18.
 */
class PostViewModel(
        val context: Application,
        private val postRepository: PostRepository
) :AndroidViewModel(context){

    val items: ObservableList<Post> = ObservableArrayList()

    fun start(){
        loadPost()
    }

    private fun loadPost(){
        postRepository.getAllPost(object : PostDataSource.LoadPostCallback{
            override fun onDataNotAvailable() {

            }

            override fun onPostLoaded(list: List<Post>) {
                with(items){
                    clear()
                    addAll(list)
                }

            }
        })
    }
}