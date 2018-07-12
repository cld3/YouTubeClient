package com.example.cld.youtueclient.dataLayer

import com.example.cld.youtueclient.MainActivity
import com.example.cld.youtueclient.playVideo.PlayVideoActivity
import com.example.cld.youtueclient.searchScreen.SearchFragment
import com.example.cld.youtueclient.searchScreen.SearchPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface DataComponent{
    fun inject(activity: MainActivity)
    fun inject(activity: PlayVideoActivity)
    fun inject(searchFragment: SearchFragment)
    fun inject(searchPresenter: SearchPresenter)
}