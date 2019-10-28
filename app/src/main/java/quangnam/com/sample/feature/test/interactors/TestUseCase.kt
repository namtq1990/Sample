package quangnam.com.sample.feature.test.interactors

import io.reactivex.Observable
import quangnam.com.sample.feature.base.interactors.BaseObservableUseCase
import quangnam.com.sample.data.network.response.ResponseWrapper
import quangnam.com.sample.data.network.response.test.DogResponse
import java.util.*
import javax.inject.Inject

/**
 * Created by quangnam on 1/14/19.
 * Project Sample
 */
open class TestUseCase
@Inject
constructor() : BaseObservableUseCase<ResponseWrapper<ArrayList<DogResponse>>>() {

    fun executeTest(): Observable<ResponseWrapper<ArrayList<DogResponse>>> {
        return buildStream(mApiHelper.allTestingData)
    }

    override fun execute(vararg params: Any?): Observable<ResponseWrapper<ArrayList<DogResponse>>> = executeTest()

}