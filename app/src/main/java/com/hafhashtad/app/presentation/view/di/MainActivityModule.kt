package com.hafhashtad.app.presentation.view.di

import androidx.fragment.app.FragmentManager
import com.hafhashtad.app.framework.di.scopes.ActivityScope
import com.hafhashtad.app.presentation.view.ui.CategoriesFragment
import com.hafhashtad.app.presentation.view.ui.MainActivity
import com.hafhashtad.app.presentation.view.ui.ProductsFragment
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule() {

    @Provides
    @ActivityScope
    fun provideFragmentManager(activity: MainActivity): FragmentManager {
        return activity.supportFragmentManager
    }

    @Provides
    @ActivityScope
    fun provideProductsFragment(): ProductsFragment {
        return ProductsFragment.newInstance()
    }

    @Provides
    @ActivityScope
    fun provideCategoriesFragment(): CategoriesFragment {
        return CategoriesFragment.newInstance()
    }

    // TODO("Provide other activity dependencies here...")
}
