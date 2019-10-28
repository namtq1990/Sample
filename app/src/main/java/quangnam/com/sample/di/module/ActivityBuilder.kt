package quangnam.com.sample.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import quangnam.com.sample.feature.test.activity.TestActivity
import quangnam.com.sample.feature.test.activity.TestActivityModule
import quangnam.com.sample.feature.test.activity.TestActivityScope

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [TestActivityModule::class, TestActivityModule.ProvideViewModel::class])
    @TestActivityScope
    abstract fun bindTestActivity(): TestActivity

//    @ContributesAndroidInjector(modules = [ViewPagerTestModule::class])
//    @ViewPagerTestScope
//    abstract fun bindTestPagerActivity(): ViewPagerTestActivity
}
