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

package quangnam.com.base.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposables;
import quangnam.com.base.Config;

/**
 * Created by quangnam on 4/18/16.
 *
 */
public class Log {
    private static String TAG;

    public static void init(Context context) {
        TAG = context.getPackageName();
    }

    //---------------------------Log Function auto Tag with formation  -----------------------------
    public static void d(String message, Object... args) {
        tag_d(TAG, message, args);
    }

    @SuppressWarnings("WeakerAccess")
    public static void e(String message, Object... args) {
        tag_e(TAG, message, args);
    }

    @SuppressWarnings("unused")
    public static void i(String message, Object... args) {
        tag_i(TAG, message, args);
    }

    @SuppressWarnings("unused")
    public static void v(String message, Object... args) {
        tag_v(TAG, message, args);
    }

    //----------------------------------------------------------------------------------------------

    //------------------------------Log function with tag and formation-----------------------------

    @SuppressWarnings("WeakerAccess")
    public static void tag_d(String tag, String message, Object... args) {
        doLogging(tag, android.util.Log.DEBUG, false, message, args);
    }

    @SuppressWarnings("WeakerAccess")
    public static void tag_e(String tag, String message, Object... args) {
        doLogging(tag, android.util.Log.ERROR, false, message, args);
    }

    @SuppressWarnings("WeakerAccess")
    public static void tag_i(String tag, String message, Object... args) {
        doLogging(tag, android.util.Log.INFO, false, message, args);
    }

    @SuppressWarnings("WeakerAccess")
    public static void tag_v(String tag, String message, Object... args) {
        doLogging(tag, android.util.Log.VERBOSE, false, message, args);
    }

    //----------------------------------------------------------------------------------------------

    @SuppressWarnings("unused")
    public static void d(boolean dumpStack, String tag, String message) {
        doLogging(tag, android.util.Log.DEBUG, dumpStack, message);
    }

    private static void doLogging(String tag, int logLevel, boolean dumpStack, String format, Object... args) {
        if (!isLoggable(logLevel, tag)) {
            return;
        }

        if (dumpStack) {
            Thread.dumpStack();
        }

        String log = args.length > 0
                ? String.format(Locale.US, format, args)
                : format;

        switch (logLevel) {
            case android.util.Log.DEBUG:
                android.util.Log.d(tag, log);
                break;
            case android.util.Log.ERROR:
                android.util.Log.e(tag, log);
                break;
            case android.util.Log.INFO:
                android.util.Log.i(tag, log);
                break;
            case android.util.Log.VERBOSE:
                android.util.Log.v(tag, log);
                break;
            default:
                break;
        }
    }

    private static boolean isLoggable(int priority) {
        return Config.DEBUG;
    }

    private static boolean isLoggable(int priority, String tag) {
        return isLoggable(priority);
    }

    public static BufferedReader loadLog() throws IOException {
        String[] cmd = {
                "logcat",
                "-d",
                "-v",
                "threadtime"
        };

        Process process = Runtime.getRuntime().exec(cmd);

        return new BufferedReader(new InputStreamReader(
                process.getInputStream()
        ));
    }

    public static Observable<String> getLogObservable() {
        return Observable.create(e -> {
            BufferedReader reader = loadLog();
            String line;

            e.setDisposable(Disposables.fromRunnable(() -> {
                try {
                    Log.d("Try to close Log stream");
                    reader.close();
                } catch (IOException e1) {
                    Log.e("Log stream couldn't be closed");
                }
            }));

            while ((line = reader.readLine()) != null) {
                if (!e.isDisposed()) {
                    e.onNext(line);
                } else {
                    e.onComplete();
                    return;
                }
            }

            e.onComplete();
        });
    }
}
