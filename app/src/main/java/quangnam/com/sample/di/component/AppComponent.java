package quangnam.com.sample.di.component;


import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import quangnam.com.sample.di.module.ActivityBuilder;
import quangnam.com.sample.di.module.AppModule;

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(quangnam.com.sample.Application application);
}
