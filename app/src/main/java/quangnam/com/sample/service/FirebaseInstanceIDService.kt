package quangnam.com.sample.service

import android.content.Context
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import dagger.android.AndroidInjection

import quangnam.com.base.utils.Log
import quangnam.com.sample.di.ApplicationContext
import javax.inject.Inject

/**
 * Created by co-well on 2/27/18.
 */

class FirebaseInstanceIDService : FirebaseInstanceIdService() {

    @Inject
    @field: ApplicationContext
    lateinit var mContext: Context

    override fun onCreate() {
        super.onCreate()
        AndroidInjection.inject(this)
    }

    override fun onTokenRefresh() {
        super.onTokenRefresh()

        val token = FirebaseInstanceId.getInstance().token
        Log.d("Firebase Token refreshed: %s", token)
    }
}
