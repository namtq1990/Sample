package quangnam.com.sample.ui.test.activity;

import quangnam.com.sample.base.IBasePresenter;
import quangnam.com.sample.base.IBaseView;

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

public interface ITestActivity {

    interface IView extends IBaseView {

    }

    interface IPresenter extends IBasePresenter<IView> {
        void getTestingData();
    }
}
