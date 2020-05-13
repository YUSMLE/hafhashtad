package com.yusmle.network.monitoring

import androidx.lifecycle.LiveData

/**
 * This liveData enabling network connectivity monitoring
 *
 * @see NetworkStateHolder to get current connection state
 */
object NetworkEvent : LiveData<Event>() {

    internal fun notify(event: Event) {
        postValue(event)
    }
}
