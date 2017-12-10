package quangnam.com.sample.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import quangnam.com.sample.di.PerActivity;
import quangnam.com.sample.ui.activity.test.TestActivity;
import quangnam.com.sample.ui.activity.test.TestActivityModule;

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {TestActivityModule.class})
    @PerActivity
    abstract TestActivity bindTestActivity();
}
