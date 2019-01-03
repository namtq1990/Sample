package quangnam.com.sample.di.module

import com.google.gson.Gson

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import quangnam.com.base.Config
import quangnam.com.sample.BuildConfig
import quangnam.com.sample.base.network.RetrofitCallAdapterFactory
import quangnam.com.sample.data.network.ApiHelper
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by quangnam on 2/28/18.
 * Project Sample
 */

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideApiConverter(gson: Gson): Converter.Factory {
        return GsonConverterFactory
                .create(gson)
    }

    @Provides
    @Singleton
    fun provideCallAdapter(factory: RetrofitCallAdapterFactory): CallAdapter.Factory {
        return factory
    }

    @Provides
    @Singleton
    fun provideApiClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (Config.DEBUG) {
            val loggerInterceptor = HttpLoggingInterceptor()
            loggerInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggerInterceptor)
        }

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(converter: Converter.Factory,
                                 client: OkHttpClient,
                                 callAdapter: CallAdapter.Factory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(converter)
                .addCallAdapterFactory(callAdapter)
                .build()
    }

    @Provides
    @Singleton
    fun provideApiHelper(retrofit: Retrofit): ApiHelper {
        return retrofit.create(ApiHelper::class.java)
    }
}
