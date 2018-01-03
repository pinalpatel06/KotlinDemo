package com.example.knoxpo.demo

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.knoxpo.demo.data.PostRepository
import com.example.knoxpo.demo.data.remote.PostRemoteDataSource
import com.example.knoxpo.demo.fragment.PostViewModel

/**
 * Created by knoxpo on 3/1/18.
 */
class ViewModelFactory private constructor(
        private val application: Application,
        private val postRepository: PostRepository

) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(PostViewModel::class.java) ->
                        PostViewModel(application, postRepository)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE ?: ViewModelFactory(application,
                            PostRepository.getInstance(PostRemoteDataSource())).also {
                        INSTANCE = it
                    }
                }

    }
}