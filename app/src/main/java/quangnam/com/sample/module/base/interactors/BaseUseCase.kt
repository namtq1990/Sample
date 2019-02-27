package quangnam.com.sample.module.base.interactors

import quangnam.com.sample.data.database.IDatabaseHelper
import quangnam.com.sample.data.network.ApiHelper
import quangnam.com.sample.data.pref.IPrefHelper
import javax.inject.Inject

/**
 * Created by quangnam on 1/14/19.
 * Project Sample
 */
abstract class BaseUseCase<T, O> {

    @Inject
    lateinit var mPrefHelper: IPrefHelper

    @Inject
    lateinit var mDatabaseHelper: IDatabaseHelper

    @Inject
    lateinit var mApiHelper: ApiHelper

    open fun buildStream(o: O) : O = o

    abstract fun execute(vararg params: Any?) : O

}