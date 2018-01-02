package com.example.knoxpo.demo.daggerPkg

import android.content.Context
import com.example.knoxpo.demo.DemoApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by knoxpo on 27/12/17.
 */

@Module
class AppModule (val app: DemoApp){

    @Provides
    @Singleton
    fun provideContext(): Context{
        return app
    }

    @Provides
    @Singleton
    fun provideApplication(): DemoApp{
        return app
    }
}