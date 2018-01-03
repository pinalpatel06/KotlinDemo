package com.example.knoxpo.demo.retrofitPkg

import com.example.knoxpo.demo.data.model.Post
import io.reactivex.Single

/**
 * Created by knoxpo on 27/12/17.
 */
interface PostApi {
    fun getPosts(no: Int): Single<Post>
    fun getAllPosts(): Single<List<Post>>
}