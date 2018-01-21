package quangnam.com.sample.di.module;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import quangnam.com.sample.di.ChildFragmentManager;
import quangnam.com.sample.di.PerFragment;

/**
 * Created by quangnam on 12/10/17.
 * Project Sample
 */

@Module
public abstract class BaseFragmentModule {

    @Provides
    @ChildFragmentManager
    @PerFragment
    static FragmentManager provideFragmentManager(Fragment fragment) {
        return fragment.getChildFragmentManager();
    }
}
