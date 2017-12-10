package quangnam.com.sample.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import quangnam.com.sample.di.ApplicationContext;

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    @ApplicationContext
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    @ApplicationContext
    quangnam.com.sample.Application provideApplication(quangnam.com.base.Application application) {
        return (quangnam.com.sample.Application) application;
    }
}
