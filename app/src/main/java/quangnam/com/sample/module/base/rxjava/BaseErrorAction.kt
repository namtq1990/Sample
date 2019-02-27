package quangnam.com.sample.module.base.rxjava

import io.reactivex.functions.Consumer
import quangnam.com.base.utils.Log

/**
 * Created by quangnam on 2/19/18.
 * Project Sample
 */

open class BaseErrorAction : Consumer<Throwable> {

    @Throws(Exception::class)
    override fun accept(throwable: Throwable) {
        Log.d("Error happened")
        throwable.printStackTrace()
    }
}
