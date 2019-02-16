package com.pretorh.myapplication.di

import com.pretorh.myapplication.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DefaultModule::class])
interface Injector {
    fun inject(viewModel: MainViewModel)
}
