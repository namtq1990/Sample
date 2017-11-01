package quangnam.com.sample.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApplication {
}
