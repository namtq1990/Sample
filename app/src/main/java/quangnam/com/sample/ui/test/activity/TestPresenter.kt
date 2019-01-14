package quangnam.com.sample.ui.test.activity

import android.databinding.ObservableField
import java.util.ArrayList

import javax.inject.Inject

import quangnam.com.base.exception.BaseException
import quangnam.com.base.utils.Log
import quangnam.com.sample.base.BasePresenter
import quangnam.com.sample.base.rxjava.BaseCompleteAction
import quangnam.com.sample.base.rxjava.BaseErrorAction
import quangnam.com.sample.base.rxjava.BaseSuccessAction
import quangnam.com.sample.data.DataManager
import quangnam.com.sample.data.network.response.ResponseWrapper
import quangnam.com.sample.data.network.response.test.DogResponse
import quangnam.com.sample.ui.test.interactors.TestUseCase

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

class TestPresenter @Inject
constructor(dataManager: DataManager) : BasePresenter<ITestActivity.IView>(dataManager), ITestActivity.IPresenter {

    val mDogs: ObservableField<String> = ObservableField<String>("")

    @Inject
    lateinit var testUseCase: TestUseCase<Any>

    override fun getTestingData() {
        disposable.add(testUseCase.build()
                .subscribe(object : BaseSuccessAction<ResponseWrapper<ArrayList<DogResponse>>>() {
                    @Throws(Exception::class)
                    override fun accept(t: ResponseWrapper<ArrayList<DogResponse>>) {
                        super.accept(t)

                        mDogs.set("")
                        for (dog in t.`object`!!) {
                            Log.d("Dog: %s", dog.value)
                            mDogs.set(mDogs.get() + "\n" + dog.value)
                        }
                    }
                },
                        object : BaseErrorAction() {
                            @Throws(Exception::class)
                            override fun accept(throwable: Throwable) {
                                super.accept(throwable)

                                if (throwable is BaseException) {
                                    view!!.onError(throwable)
                                }
                            }
                        }, BaseCompleteAction()))
    }
}
