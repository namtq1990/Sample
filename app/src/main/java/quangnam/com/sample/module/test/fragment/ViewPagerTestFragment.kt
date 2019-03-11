package quangnam.com.sample.module.test.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.fragment_view_pager_test.*
import kotlinx.android.synthetic.main.fragment_view_pager_test.view.*
import quangnam.com.sample.R
import quangnam.com.sample.di.PerChildFragment
import quangnam.com.sample.module.base.MvpFragment
import quangnam.com.sample.ui.adapter.ViewPagerFragmentAdapter
import javax.inject.Inject


class ViewPagerTestFragment : MvpFragment()
{

    lateinit var adapter: ViewPagerFragmentAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_view_pager_test, container, false)
        adapter = ViewPagerFragmentAdapter(childFragmentManager)
        var fragment = TestFragment.newInstance("A")
        adapter.addFragment(fragment, "Tille A")
        fragment = TestFragment.newInstance("B")
        adapter.addFragment(fragment, "Title B")
        rootView.pager.adapter = adapter

        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                ViewPagerTestFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
