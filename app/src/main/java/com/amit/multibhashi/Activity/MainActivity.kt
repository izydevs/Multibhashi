package com.amit.multibhashi.Activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.amit.multibhashi.Adapter.VideoAdapter
import com.amit.multibhashi.Model.ApiResponse
import com.amit.multibhashi.Model.Video
import com.amit.multibhashi.Utils.Config
import com.amit.multibhashi.ViewModel.VideoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Handler
import com.amit.multibhashi.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewSetup()
        if (Config.checkInternetConnection(this)) {
            apiCall()
        } else {
            Toast.makeText(this, resources.getString(R.string.check_internet), Toast.LENGTH_LONG).show()
            this.finishAffinity()
        }
    }

    private fun recyclerViewSetup() {
        video_rv.layoutManager = LinearLayoutManager(this)
    }

    private fun apiCall() {
        val model = ViewModelProviders.of(this).get(VideoViewModel::class.java)
        model.getVideoList()?.observe(this, Observer<ApiResponse> { apiResponse ->
            if (apiResponse!!.status.equals(Config.SUCCESS)) {
                updateUI(apiResponse.myVideoList)
                progress_bar.visibility = View.GONE
            } else if (apiResponse.status.equals(Config.FAILURE)) {
                Toast.makeText(this, resources.getString(R.string.something_wrong), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun updateUI(videoList: List<Video>?) {
        video_rv.adapter = VideoAdapter(videoList as ArrayList<Video>, this)
    }

    var doubleBackToExitPressedOnce = false

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, resources.getString(R.string.double_click_exit), Toast.LENGTH_SHORT).show()

        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}
