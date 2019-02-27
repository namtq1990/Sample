package quangnam.com.sample.module.test.activity

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import quangnam.com.sample.di.PerActivity
import quangnam.com.sample.di.PerFragment
import quangnam.com.sample.di.module.BaseActivityModule
import quangnam.com.sample.module.test.fragment.TestFragment
import quangnam.com.sample.module.test.fragment.TestFragmentModule
import quangnam.com.sample.module.test.interactors.TestUseCase
import quangnam.com.sample.module.test.modelview.TestViewModel
import javax.inject.Provider

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

@Module(includes = [BaseActivityModule::class])
abstract class TestActivityModule {

    @Binds
    @PerActivity
    abstract fun activity(activity: TestActivity): Activity

    @Module
    class ProvideViewModel {

        @Provides
        @PerActivity
        fun provideTestViewModel(testUseCase: TestUseCase) = TestViewModel(testUseCase)

        @Provides
        @PerActivity
        fun provideTestViewModelFactory(provider: Provider<TestViewModel>) = TestViewModel.Factory(provider)
    }

    /**
     * Use only if activity has [android.support.v4.app.Fragment] use dagger
     */
    @PerFragment
    @ContributesAndroidInjector(modules = [TestFragmentModule::class])
    abstract fun testFragment(): TestFragment


}
