package com.example.knoxpo.demo.fragment

import com.example.knoxpo.demo.data.model.Post


/**
 * Created by knoxpo on 4/1/18.
 */
interface PostItemUserActionListener {
    fun onPostClicked(post: Post)
}