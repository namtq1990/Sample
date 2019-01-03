package quangnam.com.sample.base.rxjava

import io.reactivex.functions.Consumer

/**
 * Created by quangnam on 2/19/18.
 * Project Sample
 */

open class BaseSuccessAction<T> : Consumer<T> {
    @Throws(Exception::class)
    override fun accept(t: T) {
        return
    }
}
