package quangnam.com.sample.module.test.fragment

import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides
import quangnam.com.sample.di.PerFragment
import quangnam.com.sample.di.module.BaseFragmentModule

/**
 * Created by quangnam on 2/27/19.
 * Project Sample
 */
@Module(includes = [BaseFragmentModule::class])
class TestViewPagerModule {

    @Provides
    @PerFragment
    fun bindFragment(fragment: TestViewPager): Fragment = fragment
}