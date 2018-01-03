package com.example.knoxpo.demo.data.remote

import android.util.Log
import com.example.knoxpo.demo.DemoApp
import com.example.knoxpo.demo.data.PostDataSource
import com.example.knoxpo.demo.data.model.Post
import com.example.knoxpo.demo.fragment.PostManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by knoxpo on 2/1/18.
 */
class PostRemoteDataSource: PostDataSource {
    @Inject lateinit var postManager: PostManager

    init {
        DemoApp.postComponent.inject(this)
    }

    override fun getAllPost(callback: PostDataSource.LoadPostCallback) {
        postManager.requestAllPost()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            t: List<Post> ->
                            Log.d("from Remote: ", t.toString())
                            callback.onPostLoaded(t)
                        },
                        {
                            e: Throwable ->
                            callback.onDataNotAvailable()
                        }
                )
    }
    /*override fun getPost(callback: PostDataSource.LoadPostCallback) {
        postManager.requestPost(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            t: Post -> callback.onPostLoaded(t)
                        },
                        {
                            e: Throwable ->
                            callback.onDataNotAvailable()
                        }
                )
    }*/
}