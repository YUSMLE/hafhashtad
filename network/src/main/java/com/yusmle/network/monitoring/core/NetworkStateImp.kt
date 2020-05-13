package com.yusmle.network.monitoring.core

import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import com.yusmle.network.monitoring.Event
import com.yusmle.network.monitoring.NetworkEvent
import com.yusmle.network.monitoring.NetworkState

/**
 * This is a static implementation of [NetworkState].
 *
 * It holds the network states and is editable, but it's only usable from this file.
 */
internal class NetworkStateImp : NetworkState {

    override var isConnected: Boolean = false
        set(value) {
            field = value
            NetworkEvent.notify(
                Event.NetworkConnectivityEvent(this)
            )
        }

    override var network: Network? = null

    override var linkProperties: LinkProperties? = null
        set(value) {
            val old = field
            field = value
            NetworkEvent.notify(
                Event.LinkPropertiesEvent(this, old)
            )
        }

    override var networkCapabilities: NetworkCapabilities? = null
        set(value) {
            val old = field
            field = value
            NetworkEvent.notify(
                Event.NetworkCapabilitiesEvent(this, old)
            )
        }
}
