package com.example.cld.youtueclient.accountScreen

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cld.youtueclient.App
import com.example.cld.youtueclient.R
import com.example.cld.youtueclient.dataLayer.YOUTUBE_API_KEY
import com.example.cld.youtueclient.dataLayer.YouTubeRepository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.account_fragment.*
import javax.inject.Inject

class AccountFragment : Fragment() {

    @Inject
    lateinit var youTubeRepository: YouTubeRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as App ).dataComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.account_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getSharedPreferences(getString(R.string.preferences_key),Context.MODE_PRIVATE)

        Picasso.get().load(sharedPref?.getString("photoUrl","nun")).into(userImage)

        username.text = sharedPref?.getString("displayName","nun")
        email.text = sharedPref?.getString("email","nun")

        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = RecyclerAdapter()
        youTubeRepository.getSubscriptionsForChanel("UCP2q5NCuu3A0B5eZoJYZFQQ", YOUTUBE_API_KEY)
                .subscribe{
                    (recycler.adapter as RecyclerAdapter).items = it.asList().toMutableList()
                    recycler.adapter?.notifyDataSetChanged()
                }

/*
        youTubeRepository.getSubscriptionsForChanel(CHANNEL_ID, YOUTUBE_API_KEY)
                .subscribe { }*/
    }
}