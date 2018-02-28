package quangnam.com.sample.util;

import android.content.Context;

import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import quangnam.com.base.exception.BaseException;
import quangnam.com.sample.di.ApplicationContext;

/**
 * Created by co-well on 2/28/18.
 */

@Singleton
public class AppUtils {
    private static final String LOCALIZED_STRING_RES_FORMAT = "error_%d";

    private final Context context;

    @Inject
    AppUtils(@ApplicationContext Context context) {
        this.context = context;
    }

    public BaseException addLocalizedException(BaseException exception) {
        String resName = String.format(Locale.getDefault(), LOCALIZED_STRING_RES_FORMAT, exception.getErrorCode());
        int resId = context.getResources().getIdentifier(resName, "string", context.getPackageName());

        if (resId != 0) {
            exception.setLocalizedMessage(context.getResources().getString(resId));
        }

        return exception;
    }

    public static BaseException addLocalizedException(Context context, BaseException exception) {
        String resName = String.format(Locale.getDefault(), LOCALIZED_STRING_RES_FORMAT, exception.getErrorCode());
        int resId = context.getResources().getIdentifier(resName, "string", context.getPackageName());

        if (resId != 0) {
            exception.setLocalizedMessage(context.getResources().getString(resId));
        }

        return exception;
    }
}
