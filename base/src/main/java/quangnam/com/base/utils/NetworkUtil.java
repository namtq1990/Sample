package quangnam.com.base.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import quangnam.com.base.exception.BaseException;

/**
 * Created by quangnam on 8/23/17.
 *
 */

public class NetworkUtil {

    public static boolean isOnline(final Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }

    public static boolean isNetworkException(final Throwable throwable) {
        try {
            return throwable instanceof IOException
                    || (Class.forName("retrofit2.HttpException")).isInstance(throwable)
                    || (throwable instanceof BaseException && ((BaseException) throwable).getErrorCode() == BaseException.RK_NETWORK_ERROR);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
