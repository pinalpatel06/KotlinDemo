package com.example.knoxpo.demo

import org.mockito.Mockito

/**
 * Created by knoxpo on 27/12/17.
 */

inline fun <reified T: Any> mock(): T = Mockito.mock(T::class.java)