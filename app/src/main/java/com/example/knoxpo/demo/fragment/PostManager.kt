package com.example.knoxpo.demo.fragment

import com.example.knoxpo.demo.data.model.Post
import com.example.knoxpo.demo.retrofitPkg.PostApi
import io.reactivex.Single
import retrofit2.http.POST
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by knoxpo on 27/12/17.
 */

@Singleton
class PostManager @Inject constructor(private val api: PostApi) {

    fun requestPost(no: Int): Single<Post> {
        return Single.create { subscriber ->
            val single: Single<Post> = api.getPosts(no)

            single
                    .subscribe { t1, t2 ->

                        if (t1 != null) {
                            subscriber.onSuccess(t1)
                        } else {
                            subscriber.onError(t2)
                        }
                    }
        }
    }

    fun requestAllPost(): Single<List<Post>> {
        return Single.create { subscriber ->
            val single: Single<List<Post>> = api.getAllPosts()

            single
                    .subscribe { t1, t2 ->
                        if(t1 != null){
                            subscriber.onSuccess(t1)
                        }else{
                            subscriber.onError(t2)
                        }
                    }

        }
    }
}