package com.example.knoxpo.demo

import android.app.Application
import com.example.knoxpo.demo.daggerPkg.AppModule
import com.example.knoxpo.demo.daggerPkg.post.DaggerPostComponent
import com.example.knoxpo.demo.daggerPkg.post.PostComponent

/**
 * Created by knoxpo on 27/12/17.
 */
class DemoApp: Application() {
    companion object {
        lateinit var postComponent: PostComponent
    }

    override fun onCreate() {
        super.onCreate()
        postComponent = DaggerPostComponent.builder()
                .appModule(AppModule(this))
                .build()

    }

}