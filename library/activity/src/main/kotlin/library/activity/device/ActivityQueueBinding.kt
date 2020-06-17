package library.activity.device

import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import library.coroutines.infrastructure.ApplicationScope
import library.activity.device.ActivityEvent.ActivityPause
import library.activity.device.ActivityEvent.ActivityResume

internal class ActivityQueueBinding(events: ActivityEvents, queue: ActivityQueue) {
    init {
        ApplicationScope().launch {
            events.get().consumeEach { event ->
                when (event) {
                    is ActivityResume -> queue.resume(event.activity)
                    is ActivityPause -> queue.pause()
                }
            }
        }
    }
}
