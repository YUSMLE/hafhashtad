package com.hafhashtad.app.framework.di.modules

import com.hafhashtad.app.framework.di.scopes.FragmentScope
import com.hafhashtad.app.presentation.view.di.CategoriesFragmentModule
import com.hafhashtad.app.presentation.view.di.ProductsFragmentModule
import com.hafhashtad.app.presentation.view.ui.CategoriesFragment
import com.hafhashtad.app.presentation.view.ui.ProductsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [ProductsFragmentModule::class])
    abstract fun bindProductsFragment(): ProductsFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [CategoriesFragmentModule::class])
    abstract fun bindCategoriesFragment(): CategoriesFragment
}
