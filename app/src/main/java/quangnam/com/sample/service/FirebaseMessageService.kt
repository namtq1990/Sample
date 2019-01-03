package quangnam.com.sample.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

import quangnam.com.base.utils.Log

/**
 * Created by co-well on 2/27/18.
 */

class FirebaseMessageService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        Log.d("Received notification")

        if (remoteMessage!!.data.size > 0) {

        }
    }
}
