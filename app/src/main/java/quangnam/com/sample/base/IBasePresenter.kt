package quangnam.com.sample.base

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

interface IBasePresenter<V : IBaseView> {
    val isViewAttached: Boolean

    fun onAttach(view: V)
    fun onDetach()
}
