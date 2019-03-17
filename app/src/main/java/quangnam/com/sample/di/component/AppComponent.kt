package quangnam.com.sample.di.component


import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import quangnam.com.sample.di.module.*
import javax.inject.Singleton

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilder::class, ServiceBuilder::class, NetModule::class, ViewModelModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: quangnam.com.sample.Application)
}
