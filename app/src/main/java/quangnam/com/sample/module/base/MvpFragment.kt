package quangnam.com.sample.module.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.widget.Toast
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

open class MvpFragment : BaseFragment(), IBaseView, HasSupportFragmentInjector      // *** NOTE:Use only if has child fragment use dagger
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
        //TODO implement
    }

    override fun hideLoading() {
        // TODO
    }

    override fun onError(exception: BaseException) {
        Toast.makeText(mActivityContext, exception.localizedMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onError(@StringRes resID: Int) {
        // TODO
    }

    override fun onError(message: String) {
        // TODO
    }

    override fun onErrorCode(errorCode: Int) {
        // TODO
    }

    override fun hideKeyboard() {
        // TODO
    }
}
