package quangnam.com.sample.feature.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import quangnam.com.base.exception.BaseException
import quangnam.com.sample.di.ActivityContext
import javax.inject.Inject

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

open class BaseFragment : quangnam.com.base.fragment.BaseFragment(),
        IBaseView,
        HasSupportFragmentInjector       // *** NOTE:Use only if has child fragment use dagger
{

    /**
     * NOTE: Use if has child fragment use dagger
     */
    @Inject
    lateinit var mChildFragmentInjector: DispatchingAndroidInjector<Fragment>

    @field:ActivityContext
    @Inject
    lateinit var mActivityContext: Context

    /**
     * NOTE: Use if has child fragment use dagger
     */
    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return mChildFragmentInjector
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun showLoading() {
        (activitySafe as? BaseActivity)?.showLoading()
    }

    override fun hideLoading() {
        (activitySafe as? BaseActivity)?.hideLoading()
    }

    override fun onError(exception: BaseException) {
        (activitySafe as? BaseActivity)?.onError(exception)
    }

    override fun onError(@StringRes resID: Int) {
        (activitySafe as? BaseActivity)?.onError(resID)
    }

    override fun onError(message: String) {
        (activitySafe as? BaseActivity)?.onError(message)
    }

    override fun onErrorCode(errorCode: Int) {
        (activitySafe as? BaseActivity)?.onErrorCode(errorCode)
    }

    override fun hideKeyboard() {
        (activitySafe as? BaseActivity)?.hideKeyboard()
    }
}
