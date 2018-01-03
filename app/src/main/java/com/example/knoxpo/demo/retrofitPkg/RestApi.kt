package com.example.knoxpo.demo.retrofitPkg

import com.example.knoxpo.demo.data.model.Post
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by knoxpo on 27/12/17.
 */
class RestApi @Inject constructor(private val retrofitInterface: RetrofitInterface): PostApi {
    override fun getAllPosts(): Single<List<Post>> {
        return retrofitInterface.getAllPost()
    }

    override fun getPosts(no: Int): Single<Post> {
        return retrofitInterface.getPost(no)
    }
}