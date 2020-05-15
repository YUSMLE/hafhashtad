package com.hafhashtad.app.presentation.view.di

import androidx.fragment.app.FragmentManager
import com.hafhashtad.app.framework.di.scopes.FragmentScope
import com.hafhashtad.app.presentation.view.ui.CategoriesFragment
import dagger.Module
import dagger.Provides

@Module
class CategoriesFragmentModule {

    @Provides
    @FragmentScope
    fun provideFragmentManager(fragment: CategoriesFragment): FragmentManager {
        return fragment.childFragmentManager
    }

    // TODO("Provide other fragment dependencies here...")
}
