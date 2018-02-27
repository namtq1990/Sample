package quangnam.com.sample.service;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import quangnam.com.base.utils.Log;

/**
 * Created by co-well on 2/27/18.
 */

public class FirebaseMessageService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d("Received notification");

        if (remoteMessage.getData().size() > 0) {

        }
    }
}
