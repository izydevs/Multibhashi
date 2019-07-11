package com.amit.multibhashi.Activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_full_screen.*
import android.widget.Toast
import com.amit.multibhashi.Utils.Config
import com.amit.multibhashi.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI


class FullScreenActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener,
    YouTubePlayer.OnFullscreenListener {

    private var playerView: YouTubePlayer? = null
    private var videoFullScreen: Boolean = false
    private var videoID: String = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)
        inits()
    }

    private fun inits() {
        player_view.initialize(Config.DEVELOPER_API_KEY, this@FullScreenActivity)
        if(intent!=null && intent.hasExtra(Config.VIDEO_ID)){
            videoID = intent.getStringExtra(Config.VIDEO_ID)
        }
    }

    override fun onFullscreen(p0: Boolean) {
        videoFullScreen = p0
    }


    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, playerView: YouTubePlayer?, p2: Boolean) {
        this.playerView = playerView
        playerView!!.setOnFullscreenListener(this)
        if (!p2) {
            playerView.loadVideo(videoID)
            playerView.fullscreenControlFlags = FULLSCREEN_FLAG_CONTROL_SYSTEM_UI
        }
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        if (p1!!.isUserRecoverableError) {
            p1.getErrorDialog(this@FullScreenActivity, 1)
        } else {
            Toast.makeText(this,resources.getString(R.string.something_wrong),Toast.LENGTH_LONG).show()        }
    }

    override fun onBackPressed() {
        if (playerView != null && videoFullScreen) {
            playerView!!.setFullscreen(false)
        } else {
            super.onBackPressed()
        }
    }

}
