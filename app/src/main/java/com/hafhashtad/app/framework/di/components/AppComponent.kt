package com.hafhashtad.app.framework.di.components

import android.app.Application
import com.hafhashtad.app.App
import com.hafhashtad.app.framework.di.modules.ActivityBuilderModule
import com.hafhashtad.app.framework.di.modules.DataSourceModule
import com.hafhashtad.app.framework.di.modules.FragmentBuilderModule
import com.hafhashtad.app.framework.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        FragmentBuilderModule::class,
        ViewModelModule::class,
        DataSourceModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun app(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}
