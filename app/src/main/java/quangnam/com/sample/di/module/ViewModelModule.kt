package quangnam.com.sample.di.module

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import quangnam.com.sample.module.test.interactors.TestUseCase
import quangnam.com.sample.module.test.modelview.TestViewModel
import kotlin.reflect.KClass

/**
 * Created by quangnam on 3/18/19.
 * Project Sample
 */
@Module
class ViewModelModule {

    @MapKey
    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Provides
    @IntoMap
    @ViewModelKey(TestViewModel::class)
    fun testViewModel(usecase: TestUseCase): ViewModel = TestViewModel(usecase)

}