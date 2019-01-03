package quangnam.com.sample.ui.test.activity

import quangnam.com.sample.base.IBasePresenter
import quangnam.com.sample.base.IBaseView

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

interface ITestActivity {

    interface IView : IBaseView

    interface IPresenter : IBasePresenter<IView> {
        fun getTestingData()
    }
}
