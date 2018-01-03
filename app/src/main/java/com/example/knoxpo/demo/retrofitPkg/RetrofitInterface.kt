package com.example.knoxpo.demo.retrofitPkg

import com.example.knoxpo.demo.data.model.Post
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by knoxpo on 25/12/17.
 */
interface RetrofitInterface {
    @GET("posts/{id}")
    fun getPost(@Path("id") id: Int): Single<Post>

    @GET("posts")
    fun getAllPost(): Single<List<Post>>
}