package com.example.knoxpo.demo.daggerPkg.post

import com.example.knoxpo.demo.retrofitPkg.PostApi
import com.example.knoxpo.demo.retrofitPkg.RestApi
import com.example.knoxpo.demo.retrofitPkg.RetrofitInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by knoxpo on 27/12/17.
 */

@Module
class PostModule {

    @Provides
    @Singleton
    fun providePostApi(retrofitInterface: RetrofitInterface): PostApi{
        return RestApi(retrofitInterface)
    }

    @Provides
    @Singleton
    fun provideBaseApi(retrofit: Retrofit): RetrofitInterface {
        return retrofit.create(RetrofitInterface::class.java)
    }
}