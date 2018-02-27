package quangnam.com.sample.service;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import quangnam.com.base.utils.Log;

/**
 * Created by co-well on 2/27/18.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("Firebase Token refreshed: %s", token);
    }
}
