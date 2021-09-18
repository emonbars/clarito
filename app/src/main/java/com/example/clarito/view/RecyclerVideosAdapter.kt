package com.example.clarito.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.recyclerview.widget.RecyclerView
import com.example.clarito.R
import com.example.clarito.Video

class RecyclerVideosAdapter(var videos : ArrayList<Video>, var resource: Int) : RecyclerView.Adapter<RecyclerVideosAdapter.VideoViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trailer_movie, parent, false)
        return VideoViewHolder(view)
    }
    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.videoWeb.loadUrl("https://www.youtube.com/embed/"+ videos[position].key + "?autoplay=0&vq=small")
    }
    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var videoWeb: WebView = itemView.findViewById(R.id.webView)
        init {
            videoWeb.getSettings().setJavaScriptEnabled(true)
            videoWeb.getSettings().setPluginState(WebSettings.PluginState.ON)
            videoWeb.setWebChromeClient(WebChromeClient())
            videoWeb.settings.javaScriptEnabled = true
            videoWeb.webChromeClient = object : WebChromeClient() {
            }
        }
    }
    override fun getItemCount(): Int {
        return videos.size
    }
}