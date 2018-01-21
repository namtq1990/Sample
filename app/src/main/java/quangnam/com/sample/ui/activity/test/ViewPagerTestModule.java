package quangnam.com.sample.ui.activity.test;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import quangnam.com.sample.di.ActivityContext;
import quangnam.com.sample.di.PerActivity;
import quangnam.com.sample.di.PerFragment;
import quangnam.com.sample.di.module.BaseActivityModule;
import quangnam.com.sample.ui.fragment.test.TestFragment;
import quangnam.com.sample.ui.fragment.test.TestFragmentModule;

/**
 * Created by quangnam on 12/12/17.
 * Project Sample
 */

@Module(includes = BaseActivityModule.class)
public abstract class ViewPagerTestModule {

    @Binds
    @PerActivity
    abstract Activity bind(ViewPagerTestActivity activity);

    @PerFragment
    @ContributesAndroidInjector(modules = TestFragmentModule.class)
    abstract TestFragment testFragment();

}
