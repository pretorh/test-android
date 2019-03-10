package com.pretorh.myapplication.di

import com.pretorh.myapplication.MainViewModel
import com.pretorh.myapplication.MyApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DefaultModule::class, PersistenceModule::class])
interface Injector {
    fun inject(application: MyApplication)
    fun inject(viewModel: MainViewModel)
}
