package com.example.cld.youtueclient.DataLayer

import com.example.cld.youtueclient.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface DataComponent{
    fun inject(activity: MainActivity)
}