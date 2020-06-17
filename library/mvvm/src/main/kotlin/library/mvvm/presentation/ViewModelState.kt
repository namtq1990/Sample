package library.mvvm.presentation

import kotlinx.coroutines.flow.Flow

interface StateViewModel<S : ViewModelState> {
    fun states(): Flow<S>
}

interface ViewModelState
