package quangnam.com.sample.ui.fragment.test;

import dagger.Module;
import dagger.Provides;

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

@Module
public class TestFragmentModule {

    @Provides
    ITestFragment.IView provideView(TestFragment fragment) {
        return fragment;
    }

    @Provides
    ITestFragment.IPresenter providePresenter(ITestFragment.IView view) {
        return new TestPresenter();
    }

}
