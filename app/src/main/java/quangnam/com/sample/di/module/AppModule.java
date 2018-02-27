package quangnam.com.sample.di.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import quangnam.com.sample.base.gson.ResponseAdapterFactory;
import quangnam.com.sample.base.gson.StringResponseParser;
import quangnam.com.sample.data.AppDataManager;
import quangnam.com.sample.data.DataManager;
import quangnam.com.sample.data.database.DatabaseHelper;
import quangnam.com.sample.data.database.IDatabaseHelper;
import quangnam.com.sample.data.network.response.StringResponse;
import quangnam.com.sample.data.pref.IPrefHelper;
import quangnam.com.sample.data.pref.PrefHelper;
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

    @Provides
    @Singleton
    Gson provideGSON() {
        GsonBuilder builder = new GsonBuilder()
                .registerTypeAdapterFactory(new ResponseAdapterFactory())
                .registerTypeHierarchyAdapter(StringResponse.class, new StringResponseParser());

        return builder.create();
    }

    @Provides
    @Singleton
    IDatabaseHelper provideDatabaseHelper(DatabaseHelper helper) {
        return helper;
    }

    @Provides
    @Singleton
    IPrefHelper providePrefHelper(PrefHelper helper) {
        return helper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager dataManager) {
        return dataManager;
    }
}
