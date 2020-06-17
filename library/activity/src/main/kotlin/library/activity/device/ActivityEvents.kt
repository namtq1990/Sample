package library.activity.device

import android.app.Activity
import android.content.Intent
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel

interface ActivityEventsSource {
    fun get(): ReceiveChannel<ActivityEvent>
}

internal interface ActivityEventsSink {
    fun post(event: ActivityEvent)
}

internal class ActivityEvents : ActivityEventsSource, ActivityEventsSink {

    private val eventsChannel = BroadcastChannel<ActivityEvent>(Channel.BUFFERED)

    override fun post(event: ActivityEvent) {
        eventsChannel.offer(event)
    }

    override fun get(): ReceiveChannel<ActivityEvent> {
        return eventsChannel.openSubscription()
    }
}

sealed class ActivityEvent {
    data class ActivityCreate(val activity: Activity) : ActivityEvent()
    data class ActivityStart(val activity: Activity) : ActivityEvent()
    data class ActivityResume(val activity: Activity) : ActivityEvent()
    data class ActivityPause(val activity: Activity) : ActivityEvent()
    data class ActivityStop(val activity: Activity) : ActivityEvent()
    data class ActivityDestroy(val activity: Activity) : ActivityEvent()
    data class ActivityResult(
        val activity: Activity,
        val requestCode: Int,
        val resultCode: Int,
        val data: Intent?
    ) : ActivityEvent()

    data class RequestPermissionsResult(
        val activity: Activity,
        val requestCode: Int,
        val permissions: List<String>,
        val grantResults: List<Int>
    ) : ActivityEvent()

    data class BackPressed(val activity: Activity) : ActivityEvent()
}
