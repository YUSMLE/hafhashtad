package com.yusmle.network.monitoring

import android.net.LinkProperties
import android.net.NetworkCapabilities

sealed class Event {

    abstract val networkState: NetworkState

    class NetworkConnectivityEvent(override val networkState: NetworkState) : Event()

    class NetworkCapabilitiesEvent(
        override val networkState: NetworkState,
        val old: NetworkCapabilities?
    ) : Event()

    class LinkPropertiesEvent(
        override val networkState: NetworkState,
        val old: LinkProperties?
    ) : Event()
}
