package quangnam.com.sample.module.test.fragment

import android.support.v4.app.Fragment

import dagger.Module
import dagger.Provides
import quangnam.com.sample.di.PerChildFragment
import quangnam.com.sample.di.PerFragment
import quangnam.com.sample.di.module.BaseFragmentModule

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

@Module(includes = [(BaseFragmentModule::class)])
class TestFragmentModule {

    @Provides
    @PerChildFragment
    fun bindFragment(fragment: TestFragment): Fragment = fragment

}
