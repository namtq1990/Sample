package library.mvvm.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onEach
import library.logger.infrastructure.logD

abstract class ReducingViewModel<S : ViewModelState>(default: S) : ViewModel(), StateViewModel<S> {

    private val states = ConflatedBroadcastChannel(default)

    final override fun states() = states.asFlow().distinctUntilChanged().onEach { logD(it) }

    protected fun last(): S = states.value

    protected fun reduce(reducer: (S) -> S) {
        states.offer(reducer(last()))
    }
}
