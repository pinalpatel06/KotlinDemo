package com.example.knoxpo.demo.model

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.google.gson.annotations.SerializedName

/**
 * Created by knoxpo on 25/12/17.
 */
data class Post(
        val _userId: Long,
        val _id: Long,
        val _title: String,
        val _body: String) /*primary constructor */ : Parcelable {

    /*
        * use field to avoid recursive call of property
        * use getter/setter to customise code
        * use default implementation of at least one of the accessors
    */
    @SerializedName("userId")
    private var mUserId: Long = _userId
        get() {
            return if (field > 0) field else -1
        }
        set(value) {
            field = if (value > 0) value else -1
        }

    @SerializedName("id")
    private var mId: Long = _id

    @SerializedName("title")
    private var mTitle: String = _title

    @SerializedName("body")
    private var mBody: String = _body

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString())

    fun getPostUserId(): Long {
        return mUserId
    }

    fun getPostTitle(): String {
        return mTitle
    }

    fun getPostBody(): String {
        return mBody
    }

    override fun toString(): String {
        return "userId: $mUserId id: $mId title: $mTitle body: $mBody"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(mUserId)
        parcel.writeLong(mId)
        parcel.writeString(mTitle)
        parcel.writeString(mBody)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Post> {
            override fun createFromParcel(parcel: Parcel): Post {
                return Post(parcel)
            }

            override fun newArray(size: Int): Array<Post?> {
                return arrayOfNulls(size)
            }
        }
    }
}