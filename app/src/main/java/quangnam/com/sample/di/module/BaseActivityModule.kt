package quangnam.com.sample.di.module

import android.app.Activity
import android.content.Context
import android.support.v4.app.FragmentManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import quangnam.com.sample.di.ActivityContext
import quangnam.com.sample.di.ActivityFragmentManager
import quangnam.com.sample.di.PerActivity
import quangnam.com.sample.module.base.BaseActivity

/**
 * Created by quangnam on 12/4/17.
 * Project Sample
 */

@Module
abstract class BaseActivityModule {

    @Binds
    @ActivityContext
    @PerActivity
    abstract fun activityContext(activity: Activity): Context

    @Module
    companion object {

        @Provides
        @ActivityFragmentManager
        @PerActivity
        @JvmStatic
        fun provideFragmentManager(activity: Activity): FragmentManager = (activity as BaseActivity).supportFragmentManager
    }
}
