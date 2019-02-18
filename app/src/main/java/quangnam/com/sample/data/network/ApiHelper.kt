package quangnam.com.sample.data.network

import io.reactivex.Observable
import quangnam.com.sample.data.network.response.ResponseWrapper
import quangnam.com.sample.data.network.response.test.DogResponse
import retrofit2.http.GET
import java.util.*

/**
 * Created by quangnam on 1/21/18.
 * Project Sample
 */

interface ApiHelper {

    /**
     * FIXME remove testing data
     */
    @get:GET("api/breed/hound/list")
    val allTestingData: Observable<ResponseWrapper<ArrayList<DogResponse>>>
}
