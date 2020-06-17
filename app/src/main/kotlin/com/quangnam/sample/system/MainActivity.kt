package com.quangnam.sample.system

import android.os.Bundle
import com.quangnam.sample.R
import library.activity.system.EventsActivity
import org.koin.android.ext.android.inject

class MainActivity: EventsActivity() {
    private val lifecycle: MainActivityLifecycle by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(lifecycle.onCreate(savedInstanceState))
        setContentView(R.layout.activity_main)
    }

}
