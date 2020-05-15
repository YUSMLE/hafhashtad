package com.yusmle.network.monitoring

import android.app.Application
import android.content.Context
import android.net.*
import com.yusmle.network.monitoring.core.ActivityLifecycleCallbacksImp
import com.yusmle.network.monitoring.core.NetworkCallbackImp
import com.yusmle.network.monitoring.core.NetworkStateImp

object NetworkStateHolder : NetworkState {

    private lateinit var holder: NetworkStateImp

    override val isConnected: Boolean
        get() = holder.isConnected

    override val network: Network?
        get() = holder.network

    override val networkCapabilities: NetworkCapabilities?
        get() = holder.networkCapabilities

    override val linkProperties: LinkProperties?
        get() = holder.linkProperties

    /**
     * This starts the broadcast of network events to NetworkState and
     * all Activity implementing NetworkConnectivityListener.
     *
     * @see NetworkState
     * @see NetworkConnectivityListener
     */
    fun Application.registerConnectivityBroadcaster() {
        holder = NetworkStateImp()

        // Register the Activity Broadcaster
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbacksImp(holder))

        // Get Connectivity Manager
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        // Register to Network Events
        connectivityManager.registerNetworkCallback(
            NetworkRequest.Builder().build(), NetworkCallbackImp(holder)
        )
    }
}
