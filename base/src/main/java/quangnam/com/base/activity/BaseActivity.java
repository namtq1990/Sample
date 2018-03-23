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

package quangnam.com.base.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.Stack;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import quangnam.com.base.fragment.AlertDialogFragment;
import quangnam.com.base.fragment.BaseDataFragment;
import quangnam.com.base.fragment.IBaseFragment;
import quangnam.com.base.interfaces.AutoUnsubscribe;
import quangnam.com.base.interfaces.IPreviousID;
import quangnam.com.base.utils.Log;

/**
 * Created by quangnam on 11/12/15.
 * <p>
 * Base class Activity use for this application.
 * Use this base class so you can handle life cycle to debug or add functional
 */
public class BaseActivity extends AppCompatActivity implements IPreviousID, AutoUnsubscribe {
    private static final String ARG_SAVE_ID = BaseActivity.class.getName() + "_savedID";

    private int mSaveID;

    private CompositeDisposable mSubscriptions = new CompositeDisposable();
    private Stack<IBaseFragment> mRequestStack;
    private ArrayList<OnFocusFragmentChanged> mFocusChangeListener;

    private AlertDialogFragment mCurAlertDialog;     // Keep current dialog instance per activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestStack = new Stack<>();

        if (savedInstanceState != null) {
            mSaveID = savedInstanceState.getInt(ARG_SAVE_ID);
        } else {
            mSaveID = hashCode();
        }

        Log.d("Activity %s onCreate", this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscriptions.dispose();
        Log.d("Activity %s onDestroy", this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Activity %s onPause", this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Activity %s onResume", this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(ARG_SAVE_ID, mSaveID);
        Log.d("Activity %s onSaveInstanceState", this);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof AlertDialogFragment) {
            mCurAlertDialog = (AlertDialogFragment) fragment;
        }
    }

    public IBaseFragment getFocusFragment() {
        return mRequestStack.isEmpty() ? null : mRequestStack.peek();
    }

    public void requestFocusFragment(IBaseFragment fragment) {
        IBaseFragment oldFragment = getFocusFragment();

        int priority = mRequestStack.indexOf(fragment);
        if (priority == -1) {
            mRequestStack.push(fragment);
        } else {
            mRequestStack.remove(fragment);
            mRequestStack.push(fragment);
        }

        if (oldFragment != fragment) {
            onFocusFragmentChange(oldFragment, fragment);
        }
    }

    public void popupFocusFragment() {
        IBaseFragment curFocus = mRequestStack.pop();
        onFocusFragmentChange(curFocus, getFocusFragment());
    }

    public void removeFocusRequest(IBaseFragment fragment) {
        IBaseFragment curFocusFragment = getFocusFragment();
        mRequestStack.remove(fragment);

        if (curFocusFragment == fragment) {
            onFocusFragmentChange(curFocusFragment, getFocusFragment());
        }
    }

    public int getPriorityFocusIndex(IBaseFragment fragment) {
        return mRequestStack.indexOf(fragment);
    }

    public void requestAtPriority(int priority, IBaseFragment fragment) {
        IBaseFragment curFocus = getFocusFragment();
        mRequestStack.add(priority, fragment);

        if (fragment == getFocusFragment()) {
            onFocusFragmentChange(curFocus, fragment);
        }
    }

    @SuppressWarnings("unused")
    public void addFocusListener(OnFocusFragmentChanged listener) {
        if (listener == null) return;
        if (mFocusChangeListener == null)
            mFocusChangeListener = new ArrayList<>();

        mFocusChangeListener.add(listener);
    }

    @SuppressWarnings("unused")
    public void removeFocusListener(OnFocusFragmentChanged listener) {
        if (mFocusChangeListener != null) {
            mFocusChangeListener.remove(listener);
        }
    }

    private void onFocusFragmentChange(IBaseFragment oldFragment, IBaseFragment newFragment) {
        if (mFocusChangeListener != null) {
            for (OnFocusFragmentChanged listener : mFocusChangeListener) {
                listener.onFocusFragmentChange(oldFragment, newFragment);
            }
        }

        if (oldFragment != null)
            oldFragment.onFocusChange(false);
        if (newFragment != null)
            newFragment.onFocusChange(true);
    }

    @Override
    public void subscribe(Disposable subscription) {
        mSubscriptions.add(subscription);
    }

    /**
     * Get current alert dialog. It help track only 1 {@link AlertDialogFragment} per {@link BaseActivity}
     */
    @Nullable
    public AlertDialogFragment getAlertDialog() {
        return mCurAlertDialog;
    }

    public void setAlertDialog(AlertDialogFragment dialog) {
        mCurAlertDialog = dialog;
    }

    @Override
    public void onBackPressed() {
        IBaseFragment fragment = getFocusFragment();

        if (fragment instanceof OnBackPressedListener) {
            ((OnBackPressedListener) fragment).onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("unused")
    public void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(color);
        } else {
            int statusBarID = getResources().getIdentifier("status_padding", null, getPackageName());
            if (statusBarID > 0) {
                View status = findViewById(statusBarID);
                if (status != null) {
                    status.setBackgroundColor(color);
                }
            }
        }
    }

    @SuppressWarnings("unused")
    public BaseDataFragment getDataFragment() {
        return null;
    }

    @Override
    public int getSaveID() {
        return mSaveID;
    }

    @SuppressWarnings("WeakerAccess")
    public interface OnBackPressedListener {
        /**
         * Function handler onBack press in fragment
         *
         * @return true if fragment handled this event
         */
        boolean onBackPressed();
    }

    @SuppressWarnings("WeakerAccess")
    public interface OnFocusFragmentChanged {
        /**
         * Listener when focus fragment changed
         *
         * @param oldFragment old focus fragment, nullable
         * @param newFragment new focus fragment, nullable
         */
        void onFocusFragmentChange(IBaseFragment oldFragment, IBaseFragment newFragment);
    }
}
