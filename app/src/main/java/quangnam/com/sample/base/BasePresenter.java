package quangnam.com.sample.base;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import quangnam.com.sample.data.DataManager;

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    final DataManager mDataManager;
    CompositeDisposable mDisposable;
    private V mView;

    @Inject
    public BasePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void onAttach(V view) {
        mView = view;
        mDisposable = new CompositeDisposable();
    }

    @Override
    public void onDetach() {
        mDisposable.dispose();
        mView = null;
    }

    @Override
    public boolean isViewAttached() {
        return mView != null;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public CompositeDisposable getDisposable() {
        return mDisposable;
    }

    public V getView() {
        return mView;
    }
}
