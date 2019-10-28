package quangnam.com.sample.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import quangnam.com.sample.feature.base.viewmodel.ViewModelFactory
import quangnam.com.sample.feature.base.viewmodel.ViewModelKey
import quangnam.com.sample.feature.test.modelview.TestViewModel
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TestViewModel::class)
    abstract fun bindTestVM(vm: TestViewModel): ViewModel

    @Binds
    @Singleton
    abstract fun bindVMFactory(vmFactory: ViewModelFactory): ViewModelProvider.Factory
}