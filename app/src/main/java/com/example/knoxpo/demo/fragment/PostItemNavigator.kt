package com.example.knoxpo.demo.fragment

import com.example.knoxpo.demo.data.model.Post

/**
 * Created by knoxpo on 3/1/18.
 */
interface PostItemNavigator {

    fun openPostDetail(post: Post)
}