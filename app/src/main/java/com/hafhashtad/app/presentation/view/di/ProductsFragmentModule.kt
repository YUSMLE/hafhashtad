package com.hafhashtad.app.presentation.view.di

import androidx.fragment.app.FragmentManager
import com.hafhashtad.app.framework.di.scopes.FragmentScope
import com.hafhashtad.app.presentation.view.ui.ProductsFragment
import dagger.Module
import dagger.Provides

@Module
class ProductsFragmentModule {

    @Provides
    @FragmentScope
    fun provideFragmentManager(fragment: ProductsFragment): FragmentManager {
        return fragment.childFragmentManager
    }

    // TODO("Provide other fragment dependencies here...")
}
