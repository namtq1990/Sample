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

package quangnam.com.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.multidex.MultiDex;

import java.lang.ref.WeakReference;
import java.util.Locale;

import quangnam.com.base.utils.Log;

/**
 * Created by quangnam on 2/27/17.
 * Project FileManager-master
 */
class BaseApplication extends Application implements Application.ActivityLifecycleCallbacks  {

    private WeakReference<Activity> mCurActivity;
    private Handler mHandler;
    private boolean mIsBackground;

    @Override
    public void onCreate() {
        super.onCreate();

        mIsBackground = false;
        mHandler = new Handler();
        Log.init(this);
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mCurActivity = new WeakReference<>(activity);
        mIsBackground = false;
    }

    @Override
    public void onActivityStarted(Activity activity) {
        mCurActivity = new WeakReference<>(activity);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        mCurActivity = new WeakReference<>(activity);
        mIsBackground = false;
    }

    @Override
    public void onActivityPaused(Activity activity) {}

    @Override
    public void onActivityStopped(Activity activity) {
        if (activity == mCurActivity.get()) {
            // This is truly activity has paused. So put it's coming to background
            mIsBackground = true;
        } else {
            mIsBackground = false;
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }

    /**
     * Return state if activity is start from background. This method is only correct if call before
     * {@link Activity#onResume()}
     */
    @SuppressWarnings("unused")
    public boolean isAppComeFromBackground() {
        return mIsBackground;
    }

    //    public boolean isAppInForeground() {
    //        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
    //        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
    //        if (appProcesses == null) {
    //            return false;
    //        }
    //        final String packageName = getPackageName();
    //        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
    //            if (appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName)) {
    //                return true;
    //            }
    //        }
    //        return false;
    //    }

    @SuppressWarnings("unused")
    public Activity getCurActivity() {
        return mCurActivity == null ? null : mCurActivity.get();
    }

    @SuppressWarnings("unused")
    public Handler getHandler() {
        return mHandler;
    }

    public Context setLanguage(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        config.locale = locale;
        resources.updateConfiguration(config, resources.getDisplayMetrics());

        return context;
    }

    @SuppressWarnings("unused")
    public void setLanguage(String language) {
        setLanguage(this, language);
    }
}

