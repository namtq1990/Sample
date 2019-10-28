package quangnam.com.sample.feature.test.fragment

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment

import dagger.Module
import dagger.Provides
import quangnam.com.sample.di.module.BaseFragmentModule
import quangnam.com.sample.feature.base.viewmodel.ViewModelFactory
import quangnam.com.sample.feature.test.modelview.TestViewModel
import javax.inject.Scope

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

@Module(includes = [(BaseFragmentModule::class)])
class TestFragmentModule {

    @Provides
    @TestFragmentScope
    fun bindFragment(fragment: TestFragment): Fragment = fragment

    @Provides
    @TestFragmentScope
    fun bindModel(fragment: TestFragment,
                  vmFactory: ViewModelFactory): TestViewModel {
        return ViewModelProviders.of(fragment, vmFactory).get(TestViewModel::class.java)
    }
}

@Scope
@Retention
annotation class TestFragmentScope
