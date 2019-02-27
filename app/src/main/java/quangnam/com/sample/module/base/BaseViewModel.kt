package quangnam.com.sample.module.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

open class BaseViewModel : ViewModel() {
    val disposable: CompositeDisposable = CompositeDisposable()

    var error = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()

    open fun onError(throwable: Throwable) {
        throwable.printStackTrace()
        error.value = throwable
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
