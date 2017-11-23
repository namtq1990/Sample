package quangnam.com.sample.ui.activity.test;

import dagger.Module;
import dagger.Provides;

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

@Module
public class TestActivityModule {

    @Provides
    ITestActivity.IView provideTestView(TestActivity activity) {
        return activity;
    }

    @Provides
    ITestActivity.IPresenter provideTestPresenter(ITestActivity.IView view) {
        return new TestPresenter();
    }
}
