package com.example.knoxpo.demo.model

/**
 * Created by knoxpo on 25/12/17.
 */
data class Post(val userId: Long, val id: Long, val title: String, val body: String) {
    var mUserId: Long
        get() = this.userId
        set(value) {
            value
        }

    val mId: Long

    var mTitle: String
        get() = this.title
        set(value) {
            value
        }

    var mBody: String
        get() = this.body
        set(value) {
            value
        }

    init {
        mUserId = userId
        mId = id
        mTitle = title
        mBody = body
    }
}