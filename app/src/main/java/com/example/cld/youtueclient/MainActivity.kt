package com.example.cld.youtueclient

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.cld.youtueclient.authorization.SignInActivity
import com.example.cld.youtueclient.playVideo.PlayVideoActivity
import com.example.cld.youtueclient.searchScreen.SearchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer,SearchFragment())
                .commit()
        startActivity(Intent(baseContext,SignInActivity::class.java))
    }
}
