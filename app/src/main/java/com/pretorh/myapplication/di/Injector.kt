package com.pretorh.myapplication.di

import com.pretorh.myapplication.MainActivity
import com.pretorh.myapplication.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DefaultModule::class, PersistenceModule::class])
interface Injector {
    fun inject(activity: MainActivity)
    fun inject(viewModel: MainViewModel)
}
