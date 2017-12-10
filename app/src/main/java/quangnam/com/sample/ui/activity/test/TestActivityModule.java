package quangnam.com.sample.ui.activity.test;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import quangnam.com.sample.di.PerActivity;
import quangnam.com.sample.di.PerFragment;
import quangnam.com.sample.di.module.BaseActivityModule;
import quangnam.com.sample.ui.fragment.test.TestFragment;
import quangnam.com.sample.ui.fragment.test.TestFragmentModule;

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

@Module(includes = BaseActivityModule.class)
public abstract class TestActivityModule {

    @Binds
    @PerActivity
    abstract Activity activity(TestActivity activity);

    @Provides
    @PerActivity
    static ITestActivity.IView provideTestView(TestActivity activity) {
        return activity;
    }

    @Provides
    @PerActivity
    static ITestActivity.IPresenter provideTestPresenter(ITestActivity.IView view) {
        return new TestPresenter();
    }

    /**
     * Use only if activity has {@link android.support.v4.app.Fragment} use dagger
     */
    @PerFragment
    @ContributesAndroidInjector(modules = TestFragmentModule.class)
    abstract TestFragment testFragment();
}
