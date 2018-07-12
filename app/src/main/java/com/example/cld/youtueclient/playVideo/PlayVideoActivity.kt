package com.example.cld.youtueclient.playVideo

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.cld.youtueclient.App
import com.example.cld.youtueclient.R
import com.example.cld.youtueclient.dataLayer.SearchListItem
import com.example.cld.youtueclient.dataLayer.YOUTUBE_API_KEY
import com.example.cld.youtueclient.dataLayer.YouTubeRepository
import com.google.android.youtube.player.*
import kotlinx.android.synthetic.main.play_video_activity.*
import javax.inject.Inject


class PlayVideoActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    @Inject
    lateinit var youTubeRepository: YouTubeRepository

    lateinit var item: SearchListItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.play_video_activity)
        (application as App).dataComponent.inject(this)

        item = intent.getSerializableExtra("item") as SearchListItem

        titleVideo.text = item.title
        description.text = item.description
        playerYouTube.initialize(YOUTUBE_API_KEY, this)

        commentRecycler.layoutManager = LinearLayoutManager(baseContext)

        youTubeRepository.getComments(item.videoId)
                .subscribe{
                    commentRecycler.adapter = CommentRecyclerAdapter()
                    (commentRecycler.adapter as CommentRecyclerAdapter).items = it.asList().toMutableList()
                    Log.d("qq",commentRecycler.adapter.toString())
                }
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider, player: YouTubePlayer, wasRestored: Boolean) {
        player.setPlayerStateChangeListener(playerStateChangeListener)
        player.setPlaybackEventListener(playbackEventListener)

        if (!wasRestored) {
            player.cueVideo(item.videoId)
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
}