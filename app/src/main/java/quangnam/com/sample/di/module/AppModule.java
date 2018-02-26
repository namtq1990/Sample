package quangnam.com.sample.di.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import quangnam.com.base.Config;
import quangnam.com.sample.BuildConfig;
import quangnam.com.sample.base.gson.ResponseAdapterFactory;
import quangnam.com.sample.base.gson.StringResponseParser;
import quangnam.com.sample.base.network.RetrofitCallAdapterFactory;
import quangnam.com.sample.data.AppDataManager;
import quangnam.com.sample.data.DataManager;
import quangnam.com.sample.data.database.DatabaseHelper;
import quangnam.com.sample.data.database.IDatabaseHelper;
import quangnam.com.sample.data.network.ApiHelper;
import quangnam.com.sample.data.network.response.StringResponse;
import quangnam.com.sample.data.pref.IPrefHelper;
import quangnam.com.sample.data.pref.PrefHelper;
import quangnam.com.sample.di.ApplicationContext;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    Converter.Factory provideApiConverter(Gson gson) {
        return GsonConverterFactory
                .create(gson);
    }

    @Provides
    @Singleton
    CallAdapter.Factory provideCallAdapter() {
        return RetrofitCallAdapterFactory.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideApiClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (Config.DEBUG) {
            HttpLoggingInterceptor loggerInterceptor = new HttpLoggingInterceptor();
        loggerInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggerInterceptor);
        }

        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory converter,
                             OkHttpClient client,
                             CallAdapter.Factory callAdapter) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(converter)
                .addCallAdapterFactory(callAdapter)
                .build();
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(Retrofit retrofit) {
        return retrofit.create(ApiHelper.class);
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
