package com.yusmle.network.monitoring.core

import android.util.Log

/**
 * Just like [runCatching], but without result
 *
 * @see runCatching
 */
internal inline fun <T> T.safeRun(TAG: String = "", block: T.() -> Unit) {
    try {
        block()
    }
    catch (e: Throwable) {
        // Ignore, but log it.
        Log.e(TAG, e.toString())
    }
}
