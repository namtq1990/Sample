package quangnam.com.sample.ui.test.activity

import android.app.Activity

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import quangnam.com.sample.di.PerActivity
import quangnam.com.sample.di.PerFragment
import quangnam.com.sample.di.module.BaseActivityModule
import quangnam.com.sample.ui.test.fragment.TestFragment
import quangnam.com.sample.ui.test.fragment.TestFragmentModule

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

@Module(includes = arrayOf(BaseActivityModule::class))
abstract class TestActivityModule {

    @Binds
    @PerActivity
    abstract fun activity(activity: TestActivity): Activity

    /**
     * Use only if activity has [android.support.v4.app.Fragment] use dagger
     */
    @PerFragment
    @ContributesAndroidInjector(modules = arrayOf(TestFragmentModule::class))
    abstract fun testFragment(): TestFragment

    @Module
    companion object {

        @Provides
        @PerActivity
        @JvmStatic
        fun provideTestView(activity: TestActivity): ITestActivity.IView {
            return activity
        }

        @Provides
        @PerActivity
        @JvmStatic
        fun provideTestPresenter(presenter: TestPresenter): ITestActivity.IPresenter {
            return presenter
        }
    }
}
