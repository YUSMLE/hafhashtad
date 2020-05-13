package com.yusmle.network.monitoring.core

import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import com.yusmle.network.monitoring.NetworkState

/**
 * Implementation of [ConnectivityManager.NetworkCallback]
 *
 * It stores every change of connectivity into [NetworkState].
 *
 * @see NetworkState
 */
internal class NetworkCallbackImp(private val holder: NetworkStateImp) :
    ConnectivityManager.NetworkCallback() {

    private fun updateConnectivity(isAvailable: Boolean, network: Network) {
        holder.network = network
        holder.isConnected = isAvailable
    }

    /**
     * In case of a new network (wifi enabled), this is called first.
     */
    override fun onAvailable(network: Network) {
        // DEBUG
        Log.i(TAG, "New Network: [$network]")

        updateConnectivity(true, network)
    }

    /**
     * This is called several times in a row, as capabilities are added step by step.
     */
    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        // DEBUG
        Log.i(TAG, "Network Capability Changed: [$network] - $networkCapabilities")

        holder.networkCapabilities = networkCapabilities
    }

    /**
     * This is called after.
     */
    override fun onLost(network: Network) {
        // DEBUG
        Log.i(TAG, "Network Lost: [$network]")

        updateConnectivity(false, network)
    }

    override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
        // DEBUG
        Log.i(
            TAG, "Network Link Properties Changed: " +
                 "[$network] - link changed: ${linkProperties.interfaceName}"
        )

        holder.linkProperties = linkProperties
    }

    override fun onUnavailable() {
        // DEBUG
        Log.i(TAG, "Network Unavailable")
    }

    override fun onLosing(network: Network, maxMsToLive: Int) {
        // DEBUG
        Log.i(TAG, "Losing With Network: [$network] - $maxMsToLive")
    }

    override fun onBlockedStatusChanged(network: Network, blocked: Boolean) {
        // DEBUG
        Log.i(TAG, "Network Blocked Status Changed: [$network] - $blocked")
    }

    companion object {
        private const val TAG = "NetworkCallbackImp"
    }
}
