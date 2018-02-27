package quangnam.com.sample.di.module;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import quangnam.com.base.Config;
import quangnam.com.sample.BuildConfig;
import quangnam.com.sample.base.network.RetrofitCallAdapterFactory;
import quangnam.com.sample.data.network.ApiHelper;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by quangnam on 2/28/18.
 * Project Sample
 */

@Module
public class NetModule {

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
}
