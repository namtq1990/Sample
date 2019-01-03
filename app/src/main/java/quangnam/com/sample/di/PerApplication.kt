package quangnam.com.sample.di

import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerApplication
