package quangnam.com.sample.ui.test.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import javax.inject.Inject

import dagger.android.support.AndroidSupportInjection
import quangnam.com.sample.base.MvpFragment

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

class TestFragment : MvpFragment(), ITestFragment.IView {

    val label: String?
        get() = arguments!!.getString(ARG_LABEL)

    @Inject
    lateinit var mPresenter: ITestFragment.IPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = TextView(inflater.context)
        rootView.text = label

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)
        mPresenter.onAttach(this)
    }

    override fun onDetach() {
        super.onDetach()
        mPresenter.onDetach()
    }

    companion object {

        private val ARG_LABEL = "label"

        fun newInstance(label: String): TestFragment {

            val args = Bundle()
            args.putString(ARG_LABEL, label)

            val fragment = TestFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
