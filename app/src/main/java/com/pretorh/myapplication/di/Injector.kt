package com.pretorh.myapplication.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DefaultModule::class])
interface Injector
