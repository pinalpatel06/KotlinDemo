package com.example.knoxpo.demo

import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

/**
 * Created by knoxpo on 27/12/17.
 */

class MockedCall<T>(
        private val ok: T? = null,
        private val error: HttpException? = null,
        private val exception: Throwable? = null,
        private val autoStart: Boolean = true,
        private val cancelException: Throwable? = null
): Call<T>{
    private var executed: Boolean = false
    private var cancled: Boolean = false
    private lateinit var callback: Callback<T>

    override fun execute(): Response<T> {
        throw IllegalStateException("Not mocked")
    }

    override fun enqueue(callback: Callback<T>) {
        this.callback = callback
        if(autoStart){
            start()
        }
    }

    fun start(){
        markAsExecuted()
        when{
            ok != null -> callback.onResponse(this, Response.success(ok))
            error != null -> callback.onResponse(this, errorResponse(error.code()))
            exception != null -> callback.onFailure(this, exception)
        }
    }

    override fun isCanceled() = cancled

    override fun isExecuted() = executed

    override fun clone(): Call<T> = throw IllegalStateException("Not Mocked")

    override fun request(): Request = throw IllegalStateException("Not Mocked")

    private fun markAsExecuted(){
        if(executed) throw IllegalStateException("Request already executed")
        executed = true
    }

    override fun cancel() {
        cancled = true
        if(cancelException != null){
            throw cancelException
        }

    }

    fun <T> errorResponse(code: Int = 400, message: String = "Error Response $code") : Response<T> =
            Response.error(code, ResponseBody.create(MediaType.parse("text/plain"), message))

    fun okHttpRespnse(code: Int = 200): okhttp3.Response = okhttp3.Response.Builder()
            .code(code)
            .protocol(Protocol.HTTP_1_1)
            .message("Mock Response")
            .request(Request.Builder().url("http://localhost").build())
            .build()


}