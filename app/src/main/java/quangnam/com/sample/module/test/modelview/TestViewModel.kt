package quangnam.com.sample.module.test.modelview

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import quangnam.com.sample.module.base.BaseViewModel
import quangnam.com.sample.data.network.response.test.DogResponse
import quangnam.com.sample.module.test.interactors.TestUseCase
import javax.inject.Provider

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

class TestViewModel(val testUseCase: TestUseCase)
    : BaseViewModel() {

    val mDogs: MutableLiveData<List<DogResponse>> = MutableLiveData()
    var mDogObservable: Observable<List<DogResponse>>? = null

    fun getTestingData() {
        if (mDogObservable == null) {
            forceGetTestingData()
        } else {
            disposable.add(mDogObservable!!.subscribe({}, { onError(it) }))
        }
    }

    fun forceGetTestingData() {
        mDogObservable = testUseCase.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoading.value = true }
                .doOnTerminate { isLoading.value = false }
                .map { t -> t.`object` as List<DogResponse> }
                .doOnNext({ t -> mDogs.value = t })
                .cache()
        disposable.add(mDogObservable!!.subscribe({ }, { onError(it) }))
    }

    class Factory(val provider: Provider<TestViewModel>) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return provider.get() as T
        }

    }

}
