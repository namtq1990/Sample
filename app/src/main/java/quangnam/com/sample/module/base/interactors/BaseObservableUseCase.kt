package quangnam.com.sample.module.base.interactors

import io.reactivex.Observable

/**
 * Created by quangnam on 1/14/19.
 * Project Sample
 */
abstract class BaseObservableUseCase<T> : BaseUseCase<T, Observable<T>>()