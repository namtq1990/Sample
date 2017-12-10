package quangnam.com.sample.base;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import quangnam.com.sample.di.ActivityContext;
import quangnam.com.sample.di.ActivityFragmentManager;
import quangnam.com.sample.di.ApplicationContext;
import quangnam.com.sample.di.PerActivity;

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

public class MvpActivity extends BaseActivity {

    @Inject
    @ApplicationContext
    Context mContext;

    @Inject
    @ActivityFragmentManager
    @PerActivity
    FragmentManager mFragmentManager;

    @Inject
    @ActivityContext
    @PerActivity
    Context mActivityContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        AndroidInjection.inject(this);
    }
}
