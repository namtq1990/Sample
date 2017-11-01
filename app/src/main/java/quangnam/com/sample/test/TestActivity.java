package quangnam.com.sample.test;

import android.content.Context;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import quangnam.com.base.fragment.AlertDialogFragment;
import quangnam.com.sample.R;
import quangnam.com.sample.base.BaseActivity;
import quangnam.com.sample.di.ApplicationContext;

public class TestActivity extends BaseActivity implements ITestPresenter.ITestView {
    private static final String TAG = TestActivity.class.getSimpleName();

    @Inject
    @ApplicationContext
    Context mContext;

    @Inject
    ITestPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        AndroidInjection.inject(this);

        // Test dialog
        AlertDialogFragment.instantiateAndShow(this,
                AlertDialogFragment.DEFAULT_ACTION,
                "Title 1",
                "First dialog",
                "OK",
                null,
                null);
        AlertDialogFragment.instantiateAndShow(this,
                AlertDialogFragment.DEFAULT_ACTION,
                "Title 2",
                "Second Dialog",
                "OK",
                null,
                null);
        AlertDialogFragment.instantiateAndShow(this,
                AlertDialogFragment.DEFAULT_ACTION,
                "Title 3",
                "Third dialog",
                "OK",
                null,
                null);
    }
}
