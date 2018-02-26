package quangnam.com.sample.data;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import quangnam.com.sample.data.database.IDatabaseHelper;
import quangnam.com.sample.data.network.ApiHelper;
import quangnam.com.sample.data.network.response.ResponseWrapper;
import quangnam.com.sample.data.network.response.test.DogResponse;
import quangnam.com.sample.data.pref.IPrefHelper;

/**
 * Created by quangnam on 1/13/18.
 * Project Sample
 */

public class AppDataManager implements DataManager, IPrefHelper {

    private final ApiHelper mApiHelper;
    private final IPrefHelper mPrefHelper;
    private final IDatabaseHelper mDatabaseHelper;

    @Inject
    AppDataManager(ApiHelper apiHelper, IPrefHelper prefHelper, IDatabaseHelper databaseHelper) {
        mApiHelper = apiHelper;
        mPrefHelper = prefHelper;
        mDatabaseHelper = databaseHelper;
    }

    @Override
    public Observable<ResponseWrapper<ArrayList<DogResponse>>> getAllTestingData() {
        return mApiHelper.getAllTestingData().observeOn(AndroidSchedulers.mainThread());
    }
}
