package quangnam.com.sample.ui.activity.test;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import quangnam.com.sample.R;
import quangnam.com.sample.base.MvpActivity;
import quangnam.com.sample.di.PerActivity;
import quangnam.com.sample.ui.adapter.ViewPagerFragmentAdapter;
import quangnam.com.sample.ui.fragment.test.TestFragment;

public class ViewPagerTestActivity extends MvpActivity implements
        HasSupportFragmentInjector // **NOTE: Use if activity has fragment need DI only
{
    /**
     * NOTE: Use if activity has fragment need DI only, ex: {@link quangnam.com.sample.base.MvpFragment}
     */
    @Inject
    @PerActivity
    DispatchingAndroidInjector<Fragment> mFragmentDispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_test);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager());

        adapter.addFragment(TestFragment.newInstance("A"), "A");
        adapter.addFragment(TestFragment.newInstance("B"), "B");


        viewPager.setAdapter(adapter);
    }

    /**
     * See {@link #mFragmentDispatchingAndroidInjector}
     */
    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentDispatchingAndroidInjector;
    }
}
