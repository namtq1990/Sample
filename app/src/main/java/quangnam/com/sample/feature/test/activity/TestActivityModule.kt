package quangnam.com.sample.feature.test.activity

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import quangnam.com.sample.di.module.BaseActivityModule
import quangnam.com.sample.feature.base.viewmodel.ViewModelFactory
import quangnam.com.sample.feature.test.fragment.TestFragment
import quangnam.com.sample.feature.test.fragment.TestFragmentModule
import quangnam.com.sample.feature.test.fragment.TestFragmentScope
import quangnam.com.sample.feature.test.modelview.TestViewModel
import javax.inject.Named
import javax.inject.Scope

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

@Module(includes = [BaseActivityModule::class])
abstract class TestActivityModule {

    @Binds
    @TestActivityScope
    abstract fun activity(activity: TestActivity): Activity

    @Module
    class ProvideViewModel {

        @Provides
        @TestActivityScope
        @Named(TestViewModel.TAG)
        fun provideTestViewModel(activity: TestActivity,
                                 vmFactory: ViewModelFactory): TestViewModel {
            return ViewModelProviders.of(activity, vmFactory).get(TestViewModel::class.java)
        }
    }

    /**
     * Use only if activity has [android.support.v4.app.Fragment] use dagger
     */
    @TestFragmentScope
    @ContributesAndroidInjector(modules = [TestFragmentModule::class])
    abstract fun testFragment(): TestFragment

}

@Scope
@Retention
annotation class TestActivityScope
