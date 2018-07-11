package com.example.cld.youtueclient.playVideo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.cld.youtueclient.R
import com.example.cld.youtueclient.dataLayer.YOUTUBE_API_KEY
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.google.android.youtube.player.YouTubePlayer.Provider
import com.google.android.youtube.player.YouTubePlayerSupportFragment


class PlayVideoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.play_video_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoId = arguments?.getString("videoId")
        initPlayer(videoId!! , childFragmentManager)
    }

    fun initPlayer(videoId: String, fragmentManager: FragmentManager) {
        val youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance()

        fragmentManager.beginTransaction()
                .add(R.id.playerContainer, youTubePlayerFragment)
                .commit()

        youTubePlayerFragment.initialize(YOUTUBE_API_KEY, object : OnInitializedListener {
            override fun onInitializationSuccess(provider: Provider, player: YouTubePlayer, wasRestored: Boolean) {
                if (!wasRestored) {
                    player.loadVideo(videoId)
                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
                }
            }

            override fun onInitializationFailure(provider: Provider, error: YouTubeInitializationResult) {
                val errorMessage = error.toString()
                Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG).show()
                Log.e("qq", errorMessage)
            }
        })

    }

    companion object {
        fun newInstance(videoId: String): PlayVideoFragment {
            val args = Bundle()
            args.putString("videoId", videoId)
            return PlayVideoFragment().apply { arguments = args }
        }
    }
}