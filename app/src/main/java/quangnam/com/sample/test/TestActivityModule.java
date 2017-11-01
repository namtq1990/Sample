package quangnam.com.sample.test;

import dagger.Module;
import dagger.Provides;

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

@Module
public class TestActivityModule {

    @Provides
    ITestPresenter.ITestView provideView(TestActivity activity) {
        return activity;
    }

    @Provides
    ITestPresenter providePresenter(ITestPresenter.ITestView view) {
        return new TestPresenter();
    }
}
