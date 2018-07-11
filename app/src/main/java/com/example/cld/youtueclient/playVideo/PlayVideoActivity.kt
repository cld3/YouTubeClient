package com.example.cld.youtueclient.playVideo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.cld.youtueclient.R
import com.example.cld.youtueclient.dataLayer.YOUTUBE_API_KEY
import com.google.android.youtube.player.*
import kotlinx.android.synthetic.main.play_video_activity.*


class PlayVideoActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.play_video_activity)

        playerQQ.initialize(YOUTUBE_API_KEY, this)
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider, player: YouTubePlayer, wasRestored: Boolean) {
        player.setPlayerStateChangeListener(playerStateChangeListener)
        player.setPlaybackEventListener(playbackEventListener)

        if (!wasRestored) {
            player.cueVideo(VIDEO_ID)
            player.play()
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider, result: YouTubeInitializationResult) {
        Log.e("qq",result.name)
    }

    private val playbackEventListener = object : YouTubePlayer.PlaybackEventListener {

        override fun onBuffering(arg0: Boolean) {}

        override fun onPaused() {}

        override fun onPlaying() {}

        override fun onSeekTo(arg0: Int) {}

        override fun onStopped() {}

    }

    private val playerStateChangeListener = object : YouTubePlayer.PlayerStateChangeListener {

        override fun onAdStarted() {}

        override fun onError(arg0: YouTubePlayer.ErrorReason) {}

        override fun onLoaded(arg0: String) {}

        override fun onLoading() {}

        override fun onVideoEnded() {}

        override fun onVideoStarted() {}
    }



    companion object {
        //http://youtu.be/<VIDEO_ID>
        val VIDEO_ID = "sncSt3IWWCM"
    }
}