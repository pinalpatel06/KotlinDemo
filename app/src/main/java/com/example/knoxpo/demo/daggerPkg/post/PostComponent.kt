package com.example.knoxpo.demo.daggerPkg.post

import com.example.knoxpo.demo.daggerPkg.AppModule
import com.example.knoxpo.demo.fragment.DetailFragment
import com.example.knoxpo.demo.fragment.MainFragment
import com.example.knoxpo.demo.retrofitPkg.RetrofitHelper
import dagger.Component
import javax.inject.Singleton

/**
 * Created by knoxpo on 27/12/17.
 */

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        PostModule::class,
        RetrofitHelper::class)
)
interface PostComponent {
    fun inject(mainFragment: MainFragment)
}