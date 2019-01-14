package quangnam.com.sample.base.interactors

import io.reactivex.Single

/**
 * Created by quangnam on 1/14/19.
 * Project Sample
 */
abstract class BaseSingleUseCase<T>: BaseUseCase<T>() {

    abstract fun build(vararg params: Any) : Single<T>
}