package com.example.cld.youtueclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cld.youtueclient.dataLayer.YouTubeRepository
import com.example.cld.youtueclient.searchScreen.SearchFragment
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer,SearchFragment())
                .commit()
    }
}
