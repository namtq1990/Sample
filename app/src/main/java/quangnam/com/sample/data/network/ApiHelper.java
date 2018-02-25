package quangnam.com.sample.data.network;

import java.util.ArrayList;

import io.reactivex.Observable;
import quangnam.com.sample.data.network.response.ResponseWrapper;
import quangnam.com.sample.data.network.response.test.DogResponse;
import retrofit2.http.GET;

/**
 * Created by quangnam on 1/21/18.
 * Project Sample
 */

public interface ApiHelper {

    /**
     * FIXME remove testing data
     */
    @GET("api/breed/hound/list")
    Observable<ResponseWrapper<ArrayList<DogResponse>>> getAllTestingData();
}
