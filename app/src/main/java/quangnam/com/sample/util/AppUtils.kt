package quangnam.com.sample.util

import android.content.Context

import java.util.Locale

import javax.inject.Inject
import javax.inject.Singleton

import quangnam.com.base.exception.BaseException
import quangnam.com.sample.di.ApplicationContext

/**
 * Created by co-well on 2/28/18.
 */

@Singleton
class AppUtils @Inject
internal constructor(@param:ApplicationContext private val context: Context) {

    fun addLocalizedException(exception: BaseException): BaseException {
        val resName = String.format(Locale.getDefault(), LOCALIZED_STRING_RES_FORMAT, exception.errorCode)
        val resId = context.resources.getIdentifier(resName, "string", context.packageName)

        if (resId != 0) {
            exception.localizedMessage = context.resources.getString(resId)
        }

        return exception
    }

    companion object {
        private val LOCALIZED_STRING_RES_FORMAT = "error_%d"

        fun addLocalizedException(context: Context, exception: BaseException): BaseException {
            val resName = String.format(Locale.getDefault(), LOCALIZED_STRING_RES_FORMAT, exception.errorCode)
            val resId = context.resources.getIdentifier(resName, "string", context.packageName)

            if (resId != 0) {
                exception.localizedMessage = context.resources.getString(resId)
            }

            return exception
        }
    }
}
