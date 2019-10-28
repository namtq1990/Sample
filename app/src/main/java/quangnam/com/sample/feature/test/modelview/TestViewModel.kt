package quangnam.com.sample.feature.test.modelview

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import quangnam.com.sample.feature.base.viewmodel.BaseViewModel
import quangnam.com.sample.data.network.response.test.DogResponse
import quangnam.com.sample.feature.test.interactors.TestUseCase
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

class TestViewModel @Inject constructor(private val testUseCase: TestUseCase)
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

    companion object {
        const val TAG = "TestVM"
    }
}
