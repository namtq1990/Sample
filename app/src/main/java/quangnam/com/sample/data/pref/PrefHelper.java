package quangnam.com.sample.data.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;

import quangnam.com.sample.di.ApplicationContext;

/**
 * Created by quangnam on 2/14/18.
 * Project Sample
 */

public class PrefHelper implements IPrefHelper {

    private final SharedPreferences mPref;

    @Inject
    public PrefHelper(@ApplicationContext  Context context) {
        this.mPref = android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(context);
    }
}
