package quangnam.com.sample.ui.test.fragment

import android.support.v4.app.Fragment

import dagger.Module
import dagger.Provides
import quangnam.com.sample.di.PerFragment
import quangnam.com.sample.di.module.BaseFragmentModule

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

@Module(includes = arrayOf(BaseFragmentModule::class))
class TestFragmentModule {

    @Provides
    @PerFragment
    fun bindFragment(fragment: TestFragment): Fragment {
        return fragment
    }

    @Provides
    @PerFragment
    fun provideView(fragment: TestFragment): ITestFragment.IView {
        return fragment
    }

    @Provides
    @PerFragment
    fun providePresenter(presenter: TestPresenter): ITestFragment.IPresenter {
        return presenter
    }

}
