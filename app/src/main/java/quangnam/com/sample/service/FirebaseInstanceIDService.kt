package quangnam.com.sample.service

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

import quangnam.com.base.utils.Log

/**
 * Created by co-well on 2/27/18.
 */

class FirebaseInstanceIDService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        super.onTokenRefresh()

        val token = FirebaseInstanceId.getInstance().token
        Log.d("Firebase Token refreshed: %s", token)
    }
}
