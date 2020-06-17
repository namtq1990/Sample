package library.activity.system

import android.content.Intent
import android.os.Bundle
import org.koin.android.ext.android.inject
import library.activity.device.ActivityEvent.ActivityCreate
import library.activity.device.ActivityEvent.ActivityDestroy
import library.activity.device.ActivityEvent.ActivityPause
import library.activity.device.ActivityEvent.ActivityResult
import library.activity.device.ActivityEvent.ActivityResume
import library.activity.device.ActivityEvent.ActivityStart
import library.activity.device.ActivityEvent.ActivityStop
import library.activity.device.ActivityEvent.BackPressed
import library.activity.device.ActivityEvent.RequestPermissionsResult
import library.activity.device.ActivityEventsSink

abstract class EventsActivity : LoggingActivity() {

    private val events: ActivityEventsSink by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        events.post(ActivityCreate(this))
    }

    override fun onStart() {
        super.onStart()
        events.post(ActivityStart(this))
    }

    override fun onResume() {
        super.onResume()
        events.post(ActivityResume(this))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        events.post(ActivityResult(this, requestCode, resultCode, data))
    }

    override fun onPause() {
        events.post(ActivityPause(this))
        super.onPause()
    }

    override fun onStop() {
        events.post(ActivityStop(this))
        super.onStop()
    }

    override fun onDestroy() {
        events.post(ActivityDestroy(this))
        super.onDestroy()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        events.post(
            RequestPermissionsResult(
                activity = this,
                requestCode = requestCode,
                permissions = permissions.toList(),
                grantResults = grantResults.toList()
            )
        )
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onBackPressed() {
        events.post(BackPressed(this))
        super.onBackPressed()
    }
}
