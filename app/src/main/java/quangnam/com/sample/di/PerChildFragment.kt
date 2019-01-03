package quangnam.com.sample.di

import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created by quangnam on 1/13/18.
 * Project Sample
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerChildFragment
