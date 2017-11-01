/*
 * MIT License
 *
 * Copyright (c) 2017 Tran Quang Nam
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package quangnam.com.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import quangnam.com.base.Application;
import quangnam.com.base.activity.BaseActivity;
import quangnam.com.base.utils.Log;

/**
 * Created by quangnam on 11/22/16.
 * Project FileManager-master
 */
class FragmentImpl implements IBaseFragment {
    private static final String TAG = FragmentImpl.class.getName();
    private static final String ARG_SAVED_HOST = TAG + "_savedHostID";

    private int mSaveID;
    private Application mAppContext;
    private BaseActivity mActivity;
    private IBaseFragment mHost;

    private CompositeDisposable mDisposable;

    FragmentImpl(IBaseFragment host) {
        mDisposable = new CompositeDisposable();
        mHost = host;
        mSaveID = hashCode();
    }

    @Override
    public void onAttach(Context context) {
        mAppContext = (Application) context.getApplicationContext();
        mActivity = (BaseActivity) context;
    }

    @Override
    public void onDetach() {
        if (mHost instanceof AlertDialogFragment) {
            // Because this fragment has a static instance, so it shouldn't keep reference to activity
            mActivity = null;
        }
    }

    @Override
    public void onCreate(Bundle savedState) {
        if (savedState != null) {
            mSaveID = savedState.getInt(ARG_SAVED_HOST);
        }

        Log.d("Fragment %s onCreate", mHost);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(ARG_SAVED_HOST, mHost.hashCode());
        Log.d("Fragment %s onSaveInstanceState", mHost);
    }

    @Override
    public void onDestroy() {
        mDisposable.dispose();
        Log.d("Fragment %s onDestroy", mHost);
    }

    @Override
    public void onStart() {
        Log.d("Fragment %s onStart", mHost);
    }

    @Override
    public void onStop() {
        Log.d("Fragment %s onStop", mHost);
    }

    @Override
    public void onResume() {
        Log.d("Fragment %s onResume", mHost);
    }

    @Override
    public void onPause() {
        Log.d("Fragment %s onPause", mHost);
    }

    @Override
    public void onFocusChange(boolean isFocus) {
        Log.d("Fragment %s focus change: %b", mHost, isFocus);
    }

    @Override
    public Context getApplicationContext() {
        return mAppContext;
    }

    @Override
    public int getSaveID() {
        return mSaveID;
    }

    @Override
    public FragmentActivity getActivitySafe() {

        return getActivity() != null
                ? getActivity()
                : (FragmentActivity) (mAppContext.getCurActivity() instanceof FragmentActivity ? mAppContext.getCurActivity() : null);
    }

    BaseActivity getActivity() {
        return mActivity;
    }

    @Override
    public void requestFocusFragment() {
        mActivity.requestFocusFragment(mHost);
    }

    @Override
    public void popupFocusFragment() {
        mActivity.popupFocusFragment();
    }

    @Override
    public void removeFocusRequest() {
        mActivity.removeFocusRequest(mHost);
    }

    @Override
    public int getPriorityFocusIndex() {
        return mActivity.getPriorityFocusIndex(mHost);
    }

    @Override
    public void requestAtPriority(int priority) {
        mActivity.requestAtPriority(priority, mHost);
    }

    @Override
    public void subscribe(Disposable disposable) {
        mDisposable.add(disposable);
    }
}
