package library.activity.di

import org.koin.dsl.binds
import org.koin.dsl.module
import library.activity.device.ActivityEvents
import library.activity.device.ActivityEventsSink
import library.activity.device.ActivityEventsSource
import library.activity.device.ActivityQueue
import library.activity.device.ActivityQueueBinding

val activityModule = module {
    single(createdAtStart = true) { ActivityQueueBinding(get(), get()) }
    single { ActivityQueue() }
    single { ActivityEvents() } binds arrayOf(
        ActivityEventsSource::class,
        ActivityEventsSink::class
    )
}
