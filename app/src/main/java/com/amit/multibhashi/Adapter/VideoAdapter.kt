package com.amit.multibhashi.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.amit.multibhashi.Activity.FullScreenActivity
import com.amit.multibhashi.Utils.Config
import com.amit.multibhashi.Model.Video
import com.amit.multibhashi.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.video_item_layout.view.*

class VideoAdapter(private val myList: ArrayList<Video>, private val context: Context) :
    RecyclerView.Adapter<VideoAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.video_item_layout, p0, false))
    }

    override fun onBindViewHolder(p0: MyViewHolder, position: Int) {
        p0.title.text = myList[position].title
        addThumbnailIcon(p0.thumbNailIcon, myList[position].video_id)
        p0.thumbNailIcon.setOnClickListener {
            startVideo(position)
        }
    }

    private fun startVideo(position: Int) {
        val intent = Intent(context, FullScreenActivity::class.java)
        intent.putExtra(Config.VIDEO_ID, myList[position].video_id)
        context.startActivity(intent)
    }


    override fun getItemCount(): Int {
        return myList.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val thumbNailIcon = view.thumb_icon!!
        val title = view.title!!
    }

    private fun addThumbnailIcon(thumbNailIcon: ImageView, s: String?) {
        val url = Config.getThumbNailUrl(s)
        Glide.with(context).load(url).into(thumbNailIcon)
    }

}
