package quangnam.com.sample.module.test.fragment

import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import quangnam.com.sample.di.PerChildFragment
import quangnam.com.sample.di.PerFragment
import quangnam.com.sample.di.module.BaseFragmentModule

/**
 * Created by quangnam on 3/10/19.
 * Project Sample
 */
@Module(includes = [BaseFragmentModule::class])
abstract class ViewPagerTestModule() {

    @Module
    class ProvideFragment() {

        @Provides
        @PerFragment
        fun bindFragment(fragment: ViewPagerTestFragment): Fragment = fragment
    }

    /**
     * Use if child fragment need DI only
     */
    @ContributesAndroidInjector(modules = [TestFragmentModule::class])
    @PerChildFragment
    abstract fun testFragment() : TestFragment
}