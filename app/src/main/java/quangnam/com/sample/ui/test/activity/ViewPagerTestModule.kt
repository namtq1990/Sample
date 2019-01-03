package quangnam.com.sample.ui.test.activity

import android.app.Activity

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import quangnam.com.sample.di.PerActivity
import quangnam.com.sample.di.PerFragment
import quangnam.com.sample.di.module.BaseActivityModule
import quangnam.com.sample.ui.test.fragment.TestFragment
import quangnam.com.sample.ui.test.fragment.TestFragmentModule

/**
 * Created by quangnam on 12/12/17.
 * Project Sample
 */

@Module(includes = arrayOf(BaseActivityModule::class))
abstract class ViewPagerTestModule {

    @Binds
    @PerActivity
    abstract fun bind(activity: ViewPagerTestActivity): Activity

    @PerFragment
    @ContributesAndroidInjector(modules = arrayOf(TestFragmentModule::class))
    abstract fun testFragment(): TestFragment

}
