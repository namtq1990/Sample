package quangnam.com.sample.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import quangnam.com.sample.base.BaseActivity;
import quangnam.com.sample.di.ActivityContext;
import quangnam.com.sample.di.ActivityFragmentManager;
import quangnam.com.sample.di.PerActivity;

/**
 * Created by quangnam on 12/4/17.
 * Project Sample
 */

@Module
public abstract class BaseActivityModule {

    @Binds
    @ActivityContext
    @PerActivity
    abstract Context activityContext(Activity activity);

    @Provides
    @ActivityFragmentManager
    @PerActivity
    static FragmentManager provideFragmentManager(Activity activity) {
        return ((BaseActivity) activity).getSupportFragmentManager();
    }
}
