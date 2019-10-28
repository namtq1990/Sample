package quangnam.com.sample.feature.test.activity

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_test.*
import quangnam.com.sample.R
import quangnam.com.sample.databinding.ActivityTestBinding
import quangnam.com.sample.feature.base.BaseActivity
import quangnam.com.sample.feature.test.fragment.TestFragment
import quangnam.com.sample.feature.test.modelview.TestViewModel
import quangnam.com.sample.ui.adapter.DogAdapter
import quangnam.com.sample.util.Navigator
import javax.inject.Inject
import javax.inject.Named

class TestActivity : BaseActivity(), HasSupportFragmentInjector  // Use if fragment need DI only
{
    @Inject
    @field:Named(TestViewModel.TAG)
    lateinit var mViewModel: TestViewModel

    @Inject
    lateinit var mFragmentAndroidInjector: DispatchingAndroidInjector<Fragment>  // If fragment need DI

    lateinit var mBinding: ActivityTestBinding

    @Inject
    lateinit var mAdapter: DogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = (DataBindingUtil.setContentView(this, R.layout.activity_test))

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
//        Navigator.navigateViewPagerActivity(this)
    }

    companion object {
        private val TAG = TestActivity::class.java.simpleName
    }
}
