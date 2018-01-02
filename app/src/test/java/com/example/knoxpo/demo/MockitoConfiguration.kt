package com.example.knoxpo.demo

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.annotations.NonNull
import org.mockito.configuration.DefaultMockitoConfiguration
import org.mockito.internal.stubbing.defaultanswers.ReturnsEmptyValues
import org.mockito.stubbing.Answer
import org.mockito.invocation.InvocationOnMock



/**
 * Created by knoxpo on 2/1/18.
 */
class MockitoConfiguration : DefaultMockitoConfiguration() {
    override fun getDefaultAnswer(): Answer<Any> {
        return object : ReturnsEmptyValues() {
            override fun answer(inv: InvocationOnMock): Any {
                val type = inv.method.returnType
                return if (type.isAssignableFrom(Observable::class.java)) {
                    Observable.error<Throwable>(createException(inv))
                } else if (type.isAssignableFrom(Single::class.java)) {
                    Single.error<Throwable>(createException(inv))
                } else {
                    super.answer(inv)
                }
            }
        }
    }

    @NonNull
    private fun createException(
            invocation: InvocationOnMock): RuntimeException {
        val s = invocation.toString()
        return RuntimeException(
                "No mock defined for invocation " + s)
    }
}