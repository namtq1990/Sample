package quangnam.com.sample.di.component


import android.app.Application

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import quangnam.com.sample.di.module.ActivityBuilder
import quangnam.com.sample.di.module.AppModule
import quangnam.com.sample.di.module.NetModule

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */
@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilder::class, NetModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: quangnam.com.sample.Application)
}
