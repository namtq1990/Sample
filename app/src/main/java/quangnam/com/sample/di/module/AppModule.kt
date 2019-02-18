package quangnam.com.sample.di.module

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapterFactory
import dagger.Module
import dagger.Provides
import quangnam.com.sample.base.gson.ResponseAdapterFactory
import quangnam.com.sample.base.gson.StringResponseParser
import quangnam.com.sample.data.database.DatabaseHelper
import quangnam.com.sample.data.database.IDatabaseHelper
import quangnam.com.sample.data.network.response.StringResponse
import quangnam.com.sample.data.pref.IPrefHelper
import quangnam.com.sample.data.pref.PrefHelper
import quangnam.com.sample.di.ApplicationContext
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

@Module
class AppModule {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideApplication(application: quangnam.com.base.Application): quangnam.com.sample.Application = application as quangnam.com.sample.Application

    @Provides
    @Named("responseAdapter")
    fun provideResponseAdapterFactory(factory: ResponseAdapterFactory): TypeAdapterFactory = factory

    @Provides
    @Singleton
    fun provideGSON(@Named("responseAdapter") factory: TypeAdapterFactory): Gson {
        val builder = GsonBuilder()
                .registerTypeAdapterFactory(factory)
                .registerTypeHierarchyAdapter(StringResponse::class.java, StringResponseParser())

        return builder.create()
    }

    @Provides
    @Singleton
    fun provideDatabaseHelper(helper: DatabaseHelper): IDatabaseHelper = helper

    @Provides
    @Singleton
    fun providePrefHelper(helper: PrefHelper): IPrefHelper = helper
}
