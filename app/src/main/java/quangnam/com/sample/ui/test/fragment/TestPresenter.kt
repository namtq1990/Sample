package quangnam.com.sample.ui.test.fragment

import javax.inject.Inject

import quangnam.com.sample.base.BasePresenter
import quangnam.com.sample.data.DataManager

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

class TestPresenter @Inject
constructor(dataManager: DataManager) : BasePresenter<ITestFragment.IView>(dataManager), ITestFragment.IPresenter
