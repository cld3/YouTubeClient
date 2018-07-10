package com.example.cld.youtueclient

import android.app.Application
import com.example.cld.youtueclient.dataLayer.DaggerDataComponent
import com.example.cld.youtueclient.dataLayer.DataComponent


class App : Application() {

    lateinit var dataComponent: DataComponent
        private  set

    override fun onCreate() {
        super.onCreate()

        dataComponent = DaggerDataComponent.builder()
                .build()
    }


}