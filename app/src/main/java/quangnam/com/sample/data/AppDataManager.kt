package quangnam.com.sample.data

import java.util.ArrayList

import javax.inject.Inject

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import quangnam.com.sample.data.database.IDatabaseHelper
import quangnam.com.sample.data.network.ApiHelper
import quangnam.com.sample.data.network.response.ResponseWrapper
import quangnam.com.sample.data.network.response.test.DogResponse
import quangnam.com.sample.data.pref.IPrefHelper

/**
 * Created by quangnam on 1/13/18.
 * Project Sample
 */

class AppDataManager @Inject
internal constructor(private val mApiHelper: ApiHelper, private val mPrefHelper: IPrefHelper, private val mDatabaseHelper: IDatabaseHelper) : DataManager, IPrefHelper {

    override val allTestingData: Observable<ResponseWrapper<ArrayList<DogResponse>>>
        get() = mApiHelper.allTestingData.observeOn(AndroidSchedulers.mainThread())
}
