package quangnam.com.sample.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import quangnam.com.sample.di.PerService
import quangnam.com.sample.service.FirebaseInstanceIDService
import quangnam.com.sample.service.FirebaseMessageService

/**
 * Created by quangnam on 1/4/19.
 * Project Sample
 */
@Module
abstract class ServiceBuilder {

    @ContributesAndroidInjector()
    @PerService
    abstract fun bindFirebaseIDService(): FirebaseInstanceIDService

    @ContributesAndroidInjector()
    @PerService
    abstract fun bindFirebaseMessageService(): FirebaseMessageService
}