package com.hafhashtad.app.common.help

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Reference:
 * [https://github.com/googlesamples/android-architecture-components]
 */

open class RxAwareViewModel : ViewModel() {

    private val disposable = CompositeDisposable()

    override fun onCleared() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }

        super.onCleared()
    }
}
