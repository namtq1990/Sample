package quangnam.com.sample.base;

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

public interface IBasePresenter<V extends IBaseView> {

    void onAttach(V view);
    void onDetach();
    boolean isViewAttached();
}
