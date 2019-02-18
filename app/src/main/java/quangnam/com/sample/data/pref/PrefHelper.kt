package quangnam.com.sample.data.pref

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.preference.PreferenceManager
import quangnam.com.sample.di.ApplicationContext
import javax.inject.Inject

/**
 * Created by quangnam on 2/14/18.
 * Project Sample
 */

class PrefHelper @Inject
constructor(@ApplicationContext context: Context) : IPrefHelper {

    private val mPref: SharedPreferences

    init {
        this.mPref = PreferenceManager.getDefaultSharedPreferences(context)
    }
}
