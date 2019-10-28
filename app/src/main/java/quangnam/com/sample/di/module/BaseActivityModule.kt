package quangnam.com.sample.di.module

import android.app.Activity
import android.content.Context
import dagger.Binds
import dagger.Module
import quangnam.com.sample.di.ActivityContext

/**
 * Created by quangnam on 12/4/17.
 * Project Sample
 */

@Module
abstract class BaseActivityModule {

    @Binds
    @ActivityContext
    abstract fun activityContext(activity: Activity): Context
}
