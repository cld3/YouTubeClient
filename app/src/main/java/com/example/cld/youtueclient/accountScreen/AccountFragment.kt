package com.example.cld.youtueclient.accountScreen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cld.youtueclient.App
import com.example.cld.youtueclient.R
import com.example.cld.youtueclient.dataLayer.CHANNEL_ID
import com.example.cld.youtueclient.dataLayer.USER_IMAGE
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

        Picasso.get().load(USER_IMAGE).into(userImage)
        username.text = USER_IMAGE

        recyvler.layoutManager = LinearLayoutManager(context)
        recyvler.adapter = RecyclerAdapter()
        youTubeRepository.getSubscriptionsForChanel("UCP2q5NCuu3A0B5eZoJYZFQQ", YOUTUBE_API_KEY)
                .subscribe{
                    (recyvler.adapter as RecyclerAdapter).items = it.asList().toMutableList()
                    recyvler.adapter?.notifyDataSetChanged()
                }

/*
        youTubeRepository.getSubscriptionsForChanel(CHANNEL_ID, YOUTUBE_API_KEY)
                .subscribe { }*/
    }
}