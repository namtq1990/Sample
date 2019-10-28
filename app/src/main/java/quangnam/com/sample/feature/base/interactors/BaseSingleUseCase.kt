package quangnam.com.sample.feature.base.interactors

import io.reactivex.Single

/**
 * Created by quangnam on 1/14/19.
 * Project Sample
 */
abstract class BaseSingleUseCase<T>: BaseUseCase<T, Single<T>>()