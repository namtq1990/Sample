package quangnam.com.sample.module.base.interactors

import io.reactivex.Maybe

/**
 * Created by quangnam on 1/14/19.
 * Project Sample
 */
abstract class BaseMaybeUseCase<T>: BaseUseCase<T, Maybe<T>>()