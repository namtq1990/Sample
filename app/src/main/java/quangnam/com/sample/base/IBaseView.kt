package quangnam.com.sample.base

import android.support.annotation.StringRes

import quangnam.com.base.exception.BaseException

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

interface IBaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(exception: BaseException)
    fun onError(@StringRes resID: Int)
    fun onError(message: String)
    fun onErrorCode(errorCode: Int)
    fun hideKeyboard()
}
