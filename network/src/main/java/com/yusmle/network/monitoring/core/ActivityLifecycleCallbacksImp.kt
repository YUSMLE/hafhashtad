package com.yusmle.network.monitoring.core

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.yusmle.network.monitoring.NetworkConnectivityListener
import com.yusmle.network.monitoring.NetworkState

/**
 * This is the implementation [Application.ActivityLifecycleCallbacks]
 *
 * It calls the methods of [NetworkConnectivityListener] in the activity implementing it,
 * thus enabling.
 *
 * @see Application.ActivityLifecycleCallbacks
 */
internal class ActivityLifecycleCallbacksImp(private val networkState: NetworkState) :
    Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) = safeRun(TAG) {

        if (activity !is LifecycleOwner) {
            return
        }

        if (activity is FragmentActivity) {
            addLifecycleCallbackToFragments(activity, networkState)
        }

        if (activity !is NetworkConnectivityListener || !activity.shouldBeCalled) {
            return
        }

        activity.onListenerCreated()
    }

    override fun onActivityStarted(activity: Activity) {
        // Nothing
    }

    override fun onActivityResumed(activity: Activity) = safeRun {

        if (activity !is LifecycleOwner) {
            return
        }

        if (activity !is NetworkConnectivityListener) {
            return
        }

        activity.onListenerResume(networkState)
    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
        // Nothing
    }

    override fun onActivityPaused(activity: Activity) {
        // Nothing
    }

    override fun onActivityStopped(activity: Activity) {
        // Nothing
    }

    override fun onActivityDestroyed(activity: Activity) {
        // Nothing
    }

    private fun addLifecycleCallbackToFragments(
        activity: FragmentActivity,
        networkState: NetworkState
    ) = safeRun(TAG) {

        val callback = object : FragmentManager.FragmentLifecycleCallbacks() {

            override fun onFragmentCreated(
                fm: FragmentManager,
                fragment: Fragment,
                savedInstanceState: Bundle?
            ) {
                if (fragment !is NetworkConnectivityListener || !fragment.shouldBeCalled) {
                    return
                }

                fragment.onListenerCreated()
            }

            override fun onFragmentResumed(fm: FragmentManager, fragment: Fragment) {
                if (fragment is NetworkConnectivityListener) {
                    fragment.onListenerResume(networkState)
                }
            }
        }

        activity.supportFragmentManager.registerFragmentLifecycleCallbacks(callback, true)
    }

    companion object {
        const val TAG = "ActivityLifecycleCallbacks"
    }
}
