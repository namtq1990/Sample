package quangnam.com.sample.feature.test.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dagger.android.support.AndroidSupportInjection
import quangnam.com.sample.feature.base.BaseFragment
import quangnam.com.sample.feature.test.modelview.TestViewModel
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

class TestFragment : BaseFragment() {

    val label: String?
        get() = arguments!!.getString(ARG_LABEL)

    @Inject
    @field:Named(TestViewModel.TAG)
    lateinit var mViewModel: TestViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = TextView(inflater.context)
        rootView.text = label

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    companion object {

        private const val ARG_LABEL = "label"

        fun newInstance(label: String): TestFragment {

            val args = Bundle()
            args.putString(ARG_LABEL, label)

            val fragment = TestFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
