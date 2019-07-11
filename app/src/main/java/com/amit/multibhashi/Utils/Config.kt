package com.amit.multibhashi.Utils

import android.content.Context
import android.net.ConnectivityManager



object Config {
    const val DEVELOPER_API_KEY = "AIzaSyCDEg7wJrow9rR_3Nrpf0Ox5Usc6MEcnRQ"
    const val BaseUrl = "https://my-json-server.typicode.com/Multibhashi/sample-api/video/"
    const val SUCCESS = "success"
    const val FAILURE = "failure"
    const val VIDEO_ID = "video_id"

    fun checkInternetConnection(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }

    fun getThumbNailUrl(videoId: String?): String {
        return "https://img.youtube.com/vi/$videoId/mqdefault.jpg"
    }
}
