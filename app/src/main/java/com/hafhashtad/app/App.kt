package com.hafhashtad.app

import android.app.Activity
import android.app.Application
import com.yusmle.network.monitoring.NetworkStateHolder.registerConnectivityBroadcaster
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        mInjector = DaggerAppComponent.builder()
            .app(this)
            .build()
        mInjector.inject(this)

        /**
         * Network Connectivity events with Android LifeCycle Components
         */
        registerConnectivityBroadcaster()
    }

    companion object {
        private lateinit var mInjector: AppComponent
    }
}
