package quangnam.com.sample.base

import javax.inject.Inject

import io.reactivex.disposables.CompositeDisposable
import quangnam.com.sample.data.DataManager

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

open class BasePresenter<V : IBaseView> @Inject
constructor(val dataManager: DataManager) : IBasePresenter<V> {
    lateinit var disposable: CompositeDisposable
        internal set
    var view: V? = null
        private set

    override val isViewAttached: Boolean
        get() = view != null

    override fun onAttach(view: V) {
        this.view = view
        disposable = CompositeDisposable()
    }

    override fun onDetach() {
        disposable.dispose()
        view = null
    }
}
