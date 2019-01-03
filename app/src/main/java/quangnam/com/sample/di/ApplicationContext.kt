package quangnam.com.sample.di

import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext
