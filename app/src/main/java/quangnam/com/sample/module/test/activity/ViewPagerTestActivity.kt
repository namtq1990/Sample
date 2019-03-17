package quangnam.com.sample.module.test.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import quangnam.com.sample.R
import quangnam.com.sample.di.PerActivity
import quangnam.com.sample.module.base.MvpActivity
import quangnam.com.sample.module.test.fragment.TestFragment
import quangnam.com.sample.ui.adapter.ViewPagerFragmentAdapter
import javax.inject.Inject

class ViewPagerTestActivity : MvpActivity(), HasSupportFragmentInjector // **NOTE: Use if activity has fragment need DI only
{
    /**
     * NOTE: Use if activity has fragment need DI only, ex: [quangnam.com.sample.base.MvpFragment]
     */
    @Inject
    @PerActivity
    lateinit var mFragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_test)

        val viewPager = findViewById<ViewPager>(R.id.pager)
        val adapter = ViewPagerFragmentAdapter(supportFragmentManager)

        adapter.addFragment(TestFragment.newInstance("A"), "A")
        adapter.addFragment(TestFragment.newInstance("B"), "B")


        viewPager.adapter = adapter
    }

    /**
     * See [.mFragmentDispatchingAndroidInjector]
     */
    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return mFragmentDispatchingAndroidInjector
    }
}
