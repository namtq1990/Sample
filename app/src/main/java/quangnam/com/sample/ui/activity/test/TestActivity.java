package quangnam.com.sample.ui.activity.test;

import android.content.Context;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import quangnam.com.base.fragment.AlertDialogFragment;
import quangnam.com.sample.R;
import quangnam.com.sample.base.BaseActivity;
import quangnam.com.sample.base.MvpActivity;
import quangnam.com.sample.di.ApplicationContext;
import quangnam.com.sample.ui.fragment.test.TestFragment;

public class TestActivity extends MvpActivity implements ITestActivity.IView {
    private static final String TAG = TestActivity.class.getSimpleName();

    @Inject
    @ApplicationContext
    Context mContext;

    @Inject
    ITestActivity.IPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        AndroidInjection.inject(this);

        TestFragment fragmentA = TestFragment.newInstance("A");
        getSupportFragmentManager().beginTransaction()
                .add(R.id.root_container, fragmentA, "A")
                .commit();
    }
}
