package quangnam.com.sample.ui.fragment.test;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TextView rootView = new TextView(inflater.getContext());
        rootView.setText(getLabel());

        return rootView;
    }

}
