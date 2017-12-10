package quangnam.com.sample.ui.activity.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import quangnam.com.sample.R;
import quangnam.com.sample.base.MvpActivity;
import quangnam.com.sample.di.PerActivity;
import quangnam.com.sample.ui.fragment.test.TestFragment;

public class TestActivity extends MvpActivity implements ITestActivity.IView,
        HasSupportFragmentInjector  // Use if fragment need DI only
{
    private static final String TAG = TestActivity.class.getSimpleName();

    @PerActivity
    @Inject
    ITestActivity.IPresenter mPresenter;

    @PerActivity
    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentAndroidInjector;  // If fragment need DI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        TestFragment fragmentA = TestFragment.newInstance("A");
        getSupportFragmentManager().beginTransaction()
                .add(R.id.root_container, fragmentA, "A")
                .commit();
    }

    // Use if Fragment need DI only
    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentAndroidInjector;
    }
}
