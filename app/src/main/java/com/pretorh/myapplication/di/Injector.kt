package com.pretorh.myapplication.di

import com.pretorh.myapplication.Fragment3ViewModel
import com.pretorh.myapplication.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DefaultModule::class, PersistenceModule::class])
interface Injector {
    fun inject(viewModel: MainViewModel)
    fun inject(viewModel: Fragment3ViewModel)
}
