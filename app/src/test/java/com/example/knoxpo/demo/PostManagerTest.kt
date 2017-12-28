package com.example.knoxpo.demo

import android.util.Log
import com.example.knoxpo.demo.fragment.PostManager
import com.example.knoxpo.demo.model.Post
import com.example.knoxpo.demo.retrofitPkg.PostApi
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.DisposableSubscriber
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.POST

/**
 * Created by knoxpo on 27/12/17.
 */
class PostManagerTest {

    var apiMock = mock<PostApi>()
    var testSub = TestObserver<Post>()
    var callMock = mock<Call<Post>>()

    @Before
    fun setup(){
        var apiMock = mock<PostApi>()
        var testSub = TestObserver<Post>()
        var callMock = mock<Single<Post>>()
        `when`(apiMock.getPosts(0)).thenReturn(callMock)
    }

    @Test
    fun testSuccess_basic() {
        val p = Post(1,5, "ggg", "rrr")
        val response = Response.success(p)

        val res = `when`(callMock.execute()).thenReturn(response)


        val postManager = PostManager(apiMock)
        postManager.requestPost(0).subscribe(testSub)

        testSub.assertNoErrors()
       // testSub.assertValueCount(1)
       // testSub.assertComplete()
    }
}