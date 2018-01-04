package com.example.knoxpo.demo.fragment

import android.view.View
import com.example.knoxpo.demo.data.model.Post
import retrofit2.http.POST

/**
 * Created by knoxpo on 4/1/18.
 */
interface PostItemUserActionListener {
    fun onPostClicked(post: Post)
}