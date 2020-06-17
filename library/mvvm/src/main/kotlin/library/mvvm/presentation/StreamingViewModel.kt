package library.mvvm.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.broadcastIn
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onEach
import library.logger.infrastructure.logD

abstract class StreamingViewModel<S : ViewModelState> : ViewModel(), StateViewModel<S> {

    private var channel: BroadcastChannel<S>? = null

    final override fun states() = channel().asFlow().distinctUntilChanged().onEach { logD(it) }

    protected abstract fun createStates(): Flow<S>

    private fun channel(): BroadcastChannel<S> {
        return channel ?: createStates()
            .conflate()
            .broadcastIn(viewModelScope)
            .also { channel = it }
    }
}
