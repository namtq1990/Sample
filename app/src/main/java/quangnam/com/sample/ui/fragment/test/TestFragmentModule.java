package quangnam.com.sample.ui.fragment.test;

import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;
import quangnam.com.sample.di.PerFragment;
import quangnam.com.sample.di.module.BaseFragmentModule;

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

@Module(includes = {BaseFragmentModule.class})
public class TestFragmentModule {

    @Provides
    @PerFragment
    Fragment bindFragment(TestFragment fragment) {
        return fragment;
    }

    @Provides
    @PerFragment
    ITestFragment.IView provideView(TestFragment fragment) {
        return fragment;
    }

    @Provides
    @PerFragment
    ITestFragment.IPresenter providePresenter(ITestFragment.IView view) {
        return new TestPresenter();
    }

}
