package quangnam.com.sample.module.test.activity

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import quangnam.com.sample.di.PerActivity
import quangnam.com.sample.di.PerFragment
import quangnam.com.sample.di.module.BaseActivityModule
import quangnam.com.sample.module.test.fragment.ViewPagerTestFragment
import quangnam.com.sample.module.test.fragment.ViewPagerTestModule

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

@Module(includes = [BaseActivityModule::class])
abstract class TestActivityModule {

    @Binds
    @PerActivity
    abstract fun activity(activity: TestActivity): Activity

    /**
     * Use only if activity has [android.support.v4.app.Fragment] use dagger
     */
    @PerFragment
    @ContributesAndroidInjector(modules = [ViewPagerTestModule::class])
    abstract fun testFragment(): ViewPagerTestFragment


}
