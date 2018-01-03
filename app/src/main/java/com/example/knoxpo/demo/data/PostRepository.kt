package com.example.knoxpo.demo.data

import com.example.knoxpo.demo.data.model.Post

/**
 * Created by knoxpo on 3/1/18.
 */
class PostRepository(val postRemoteDataSource: PostDataSource) : PostDataSource {

    val cachePost: LinkedHashMap<String, Post> = LinkedHashMap()

    override fun getAllPost(callback: PostDataSource.LoadPostCallback) {
        if (cachePost.isNotEmpty()) {
            callback.onPostLoaded(ArrayList(cachePost.values))
            return
        } else {
            postRemoteDataSource.getAllPost(object : PostDataSource.LoadPostCallback {
                override fun onDataNotAvailable() {

                }

                override fun onPostLoaded(list: List<Post>) {
                    callback.onPostLoaded(ArrayList(list))
                }
            })

        }
    }

    companion object {

        private var INSTANCE: PostRepository? = null

        @JvmStatic fun getInstance(postRemoteDataSource: PostDataSource) =
                INSTANCE ?: synchronized(PostRepository::class.java) {
                    INSTANCE ?: PostRepository(postRemoteDataSource)
                            .also { INSTANCE = it }
                }

        @JvmStatic fun destroyInstance() {
            INSTANCE = null
        }
    }
}