package quangnam.com.sample.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;
import quangnam.com.sample.di.ActivityContext;
import quangnam.com.sample.di.ChildFragmentManager;
import quangnam.com.sample.di.PerFragment;

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

public class MvpFragment extends BaseFragment implements IBaseView,
        HasSupportFragmentInjector      // *** NOTE:Use only if has child fragment use dagger
{

    /**
     * NOTE: Use if has child fragment use dagger
     */
    @Inject
    DispatchingAndroidInjector<Fragment> mChildFragmentInjector;

    @ActivityContext
    @Inject
    protected Context mActivityContext;

    @PerFragment
    @ChildFragmentManager
    @Inject
    protected FragmentManager mChildFragmentManager;

    /**
     * NOTE: Use if has child fragment use dagger
     */
    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mChildFragmentInjector;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(@StringRes int resID) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onErrorCode(int errorCode) {

    }

    @Override
    public void hideKeyboard() {

    }
}
