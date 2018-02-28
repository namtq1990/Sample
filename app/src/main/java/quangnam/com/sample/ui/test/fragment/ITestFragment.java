package quangnam.com.sample.ui.test.fragment;

import quangnam.com.sample.base.IBasePresenter;
import quangnam.com.sample.base.IBaseView;

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

public interface ITestFragment {

    interface IView extends IBaseView {

    }

    interface IPresenter extends IBasePresenter<IView> {

    }
}
