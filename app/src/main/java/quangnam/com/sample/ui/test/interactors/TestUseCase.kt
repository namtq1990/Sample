package quangnam.com.sample.ui.test.interactors

import io.reactivex.Observable
import quangnam.com.sample.base.interactors.BaseObservableUseCase
import quangnam.com.sample.data.network.response.ResponseWrapper
import quangnam.com.sample.data.network.response.test.DogResponse
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by quangnam on 1/14/19.
 * Project Sample
 */
open class TestUseCase<T>
@Inject
constructor() : BaseObservableUseCase<ResponseWrapper<ArrayList<quangnam.com.sample.data.network.response.test.DogResponse>>>() {

    override fun build(vararg params: Any): Observable<ResponseWrapper<ArrayList<quangnam.com.sample.data.network.response.test.DogResponse>>> = mApiHelper.allTestingData

    fun build(): Observable<ResponseWrapper<ArrayList<quangnam.com.sample.data.network.response.test.DogResponse>>> = mApiHelper.allTestingData

}