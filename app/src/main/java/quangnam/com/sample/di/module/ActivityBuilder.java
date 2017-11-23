package quangnam.com.sample.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import quangnam.com.sample.ui.activity.test.TestActivity;
import quangnam.com.sample.ui.activity.test.TestActivityModule;
import quangnam.com.sample.ui.fragment.test.TestFragment;
import quangnam.com.sample.ui.fragment.test.TestFragmentModule;

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = TestActivityModule.class)
    abstract TestActivity bindTestActivity();

    @ContributesAndroidInjector(modules = TestFragmentModule.class)
    abstract TestFragment bindTestFragment();
}
