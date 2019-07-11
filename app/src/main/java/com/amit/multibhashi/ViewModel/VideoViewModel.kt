package com.amit.multibhashi.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.amit.multibhashi.Utils.Config
import com.amit.multibhashi.Interface.VideoService
import com.amit.multibhashi.Model.ApiResponse
import com.amit.multibhashi.Model.Video
import com.amit.multibhashi.Utils.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoViewModel : ViewModel() {

    private val videoList: MutableLiveData<ApiResponse>? = MutableLiveData()

    fun getVideoList(): MutableLiveData<ApiResponse>? {
        loadUsers()
        return videoList
    }

    private fun loadUsers() {
        val service = APIClient.getClient()!!.create(VideoService::class.java)
        val call = service.getVideoList()
        val apiResponse = ApiResponse()
        call.enqueue(object : Callback<List<Video>> {
            override fun onResponse(call: Call<List<Video>>, response: Response<List<Video>>) {
                if (response.body() != null && response.body()!!.isNotEmpty()) {
                    apiResponse.myVideoList = response.body()
                    apiResponse.status = Config.SUCCESS
                    videoList!!.postValue(apiResponse)
                }
            }

            override fun onFailure(call: Call<List<Video>>, t: Throwable) {
                apiResponse.status = Config.FAILURE
                videoList!!.postValue(apiResponse)
            }
        })

    }
}