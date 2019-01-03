package quangnam.com.sample.ui.test.activity

import android.os.Bundle
import android.support.v4.app.Fragment

import javax.inject.Inject

import butterknife.ButterKnife
import butterknife.OnClick
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import quangnam.com.sample.R
import quangnam.com.sample.base.MvpActivity
import quangnam.com.sample.di.PerActivity
import quangnam.com.sample.ui.test.fragment.TestFragment
import quangnam.com.sample.util.Navigator

class TestActivity : MvpActivity(), ITestActivity.IView, HasSupportFragmentInjector  // Use if fragment need DI only
{

    @PerActivity
    @Inject
    lateinit var mPresenter: ITestActivity.IPresenter

    @PerActivity
    @Inject
    lateinit var mFragmentAndroidInjector: DispatchingAndroidInjector<Fragment>  // If fragment need DI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val fragmentA = TestFragment.newInstance("A")
        supportFragmentManager.beginTransaction()
                .add(R.id.root_container, fragmentA, "A")
                .commit()

        ButterKnife.bind(this)
        mPresenter.onAttach(this)

        mPresenter.getTestingData()
    }

    override fun onDestroy() {
        super.onDestroy()

        mPresenter.onDetach()
    }

    // Use if Fragment need DI only
    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return mFragmentAndroidInjector
    }

    @OnClick(R.id.btn_viewpager_test)
    fun openViewPagerPage() {
        Navigator.navigateViewPagerActivity(this)
    }

    companion object {
        private val TAG = TestActivity::class.java.simpleName
    }
}
