package com.example.knoxpo.demo

import com.example.knoxpo.demo.fragment.PostManager
import com.example.knoxpo.demo.data.model.Post
import com.example.knoxpo.demo.retrofitPkg.PostApi
import io.reactivex.*
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.mockito.Mockito.`when`

/**
 * Created by knoxpo on 27/12/17.
 */
class PostManagerTest {

    var apiMock = mock<PostApi>()
    var testSub = TestObserver<Post>()

  /*  @Before
    fun setup(){
        var apiMock = mock<PostApi>()
        var callMock = mock<Single<Post>>()
        `when`(apiMock.getPosts(0)).thenReturn(callMock)
    }*/

    @Test
    fun testSuccess_basic() {
        val p = Post(1, 5, "ggg", "rrr")

        `when`(apiMock.getPosts(1)).thenReturn(
                Single.just(p)
        )

        val postManager = PostManager(apiMock)
        postManager.requestPost(1).subscribe(testSub)

        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertComplete()
    }

  /*  @Test
    fun emptyTest() {
        apiMock.getPosts(1).subscribe(testSub)

        testSub.assertError(NullPointerException())

    }*/
}