package quangnam.com.sample.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
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

public class MvpActivity extends BaseActivity implements IBaseView {

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
    }

    @Override
    public void showLoading() {
        //TODO impelement
    }

    @Override
    public void hideLoading() {
        //TODO implement
    }

    @Override
    public void onError(@StringRes int resID) {
        //TODO implement
    }

    @Override
    public void onError(String message) {
        //TODO implement
    }

    @Override
    public void onErrorCode(int errorCode) {
        //TODO implement
    }

    @Override
    public void hideKeyboard() {
        //TODO implement
    }
}
