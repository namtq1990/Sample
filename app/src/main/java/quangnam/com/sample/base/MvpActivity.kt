package quangnam.com.sample.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.FragmentManager
import android.widget.Toast
import dagger.android.AndroidInjection
import quangnam.com.base.exception.BaseException
import quangnam.com.sample.di.ActivityContext
import quangnam.com.sample.di.ActivityFragmentManager
import quangnam.com.sample.di.ApplicationContext
import quangnam.com.sample.di.PerActivity
import javax.inject.Inject

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

abstract class MvpActivity : BaseActivity(), IBaseView {

    @Inject
    @field:ApplicationContext
    lateinit var mContext: Context

    @Inject
    @field:ActivityFragmentManager
    @PerActivity
    lateinit var mFragmentManager: FragmentManager

    @Inject
    @field:ActivityContext
    @PerActivity
    lateinit var mActivityContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
    }

    override fun showLoading() {
        //TODO impelement
    }

    override fun hideLoading() {
        //TODO implement
    }

    override fun onError(exception: BaseException) {
        Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onError(@StringRes resID: Int) {
        //TODO implement
    }

    override fun onError(message: String) {
        //TODO implement
    }

    override fun onErrorCode(errorCode: Int) {
        //TODO implement
    }

    override fun hideKeyboard() {
        //TODO implement
    }
}
