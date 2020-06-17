package library.scope.di

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

inline fun <reified T : ViewModel> LifecycleOwner.viewModel(scope: ScopeControl): Lazy<T> {
    return scope.get().viewModel(this)
}
