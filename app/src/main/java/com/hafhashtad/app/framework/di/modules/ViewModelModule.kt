package com.hafhashtad.app.framework.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hafhashtad.app.common.help.ViewModelFactory
import com.hafhashtad.app.framework.di.keys.ViewModelKey
import com.hafhashtad.app.presentation.viewmodel.MainVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewModelKey(MainVM::class)
    abstract fun provideMainViewModel(mainVM: MainVM): ViewModel
}
