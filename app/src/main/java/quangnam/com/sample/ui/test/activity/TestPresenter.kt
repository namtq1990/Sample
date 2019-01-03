package quangnam.com.sample.ui.test.activity

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

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

class TestPresenter @Inject
constructor(dataManager: DataManager) : BasePresenter<ITestActivity.IView>(dataManager), ITestActivity.IPresenter {

    override fun getTestingData() {
        disposable.add(dataManager.allTestingData
                .subscribe(object : BaseSuccessAction<ResponseWrapper<ArrayList<DogResponse>>>() {
                    @Throws(Exception::class)
                    override fun accept(t: ResponseWrapper<ArrayList<DogResponse>>) {
                        super.accept(t)

                        for (dog in t.`object`!!) {
                            Log.d("Dog: %s", dog.value)
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
