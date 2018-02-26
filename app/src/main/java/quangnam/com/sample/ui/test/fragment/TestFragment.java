package quangnam.com.sample.ui.test.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import quangnam.com.sample.base.MvpFragment;

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

public class TestFragment extends MvpFragment implements ITestFragment.IView {

    private static final String ARG_LABEL = "label";

    public static TestFragment newInstance(String label) {

        Bundle args = new Bundle();
        args.putString(ARG_LABEL, label);

        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public String getLabel() {
        return getArguments().getString(ARG_LABEL);
    }

    @Inject
    ITestFragment.IPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TextView rootView = new TextView(inflater.getContext());
        rootView.setText(getLabel());

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AndroidSupportInjection.inject(this);
        mPresenter.onAttach(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.onDetach();
    }
}
