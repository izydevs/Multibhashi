package com.amit.multibhashi.Interface

import com.amit.multibhashi.Model.Video
import retrofit2.Call
import retrofit2.http.GET

internal interface VideoService {

    @GET(".")
    fun getVideoList(): Call<List<Video>>

}