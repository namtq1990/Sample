package quangnam.com.sample.module.test.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_test.*
import quangnam.com.sample.R
import quangnam.com.sample.module.base.MvpActivity
import quangnam.com.sample.databinding.ActivityTestBinding
import quangnam.com.sample.di.PerActivity
import quangnam.com.sample.ui.adapter.DogAdapter
import quangnam.com.sample.module.test.fragment.TestFragment
import quangnam.com.sample.module.test.modelview.TestViewModel
import quangnam.com.sample.util.Navigator
import javax.inject.Inject

class TestActivity : MvpActivity(), HasSupportFragmentInjector  // Use if fragment need DI only
{
    lateinit var mViewModel: TestViewModel

    @PerActivity
    @Inject
    lateinit var mViewModelFactory: TestViewModel.Factory

    @PerActivity
    @Inject
    lateinit var mFragmentAndroidInjector: DispatchingAndroidInjector<Fragment>  // If fragment need DI

    @PerActivity
    @Inject
    lateinit var mAdapter: DogAdapter

    lateinit var mBinding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = (DataBindingUtil.setContentView(this, R.layout.activity_test))
        mViewModel = ViewModelProviders.of(this, mViewModelFactory)[TestViewModel::class.java]

        mBinding.apply {
            mBinding.data = mViewModel
            mBinding.view = this@TestActivity
            mBinding.setLifecycleOwner(this@TestActivity)
        }

        initView()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.unbind()
    }

    fun initView() {
        list_dogs.layoutManager = LinearLayoutManager(this)
        list_dogs.adapter = mAdapter

        val fragmentA = TestFragment.newInstance("A")
        supportFragmentManager.beginTransaction()
                .add(R.id.root_container, fragmentA, "A")
                .commit()
    }

    fun initData() {
        mViewModel.getTestingData()
        mViewModel.mDogs.observe(this, Observer { t ->
            mAdapter.data = t ?: ArrayList()
        })
    }

    // Use if Fragment need DI only
    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return mFragmentAndroidInjector
    }

    fun openViewPagerPage() {
        Navigator.navigateViewPagerActivity(this)
    }

    companion object {
        private val TAG = TestActivity::class.java.simpleName
    }
}
