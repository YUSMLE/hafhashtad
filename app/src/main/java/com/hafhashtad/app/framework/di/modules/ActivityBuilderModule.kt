package com.hafhashtad.app.framework.di.modules

import com.hafhashtad.app.presentation.view.ui.MainActivity
import com.hafhashtad.app.framework.di.scopes.ActivityScope
import com.hafhashtad.app.presentation.view.di.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}
