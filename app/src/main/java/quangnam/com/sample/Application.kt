package quangnam.com.sample

import android.app.Activity
import com.crashlytics.android.Crashlytics
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.fabric.sdk.android.Fabric
import quangnam.com.sample.di.component.DaggerAppComponent
import javax.inject.Inject

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

class Application : quangnam.com.base.Application(), HasActivityInjector {

    @Inject
    lateinit var mActivityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return mActivityDispatchingAndroidInjector
    }
}
