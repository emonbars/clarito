package com.example.clarito

import com.google.gson.JsonObject
import java.lang.Exception
import java.io.Serializable

class Video(videosJson: JsonObject?) : Serializable {

    lateinit var id: String
    lateinit var key: String

    init {
        try {
            id  = videosJson!!.get(ID).asString
            key = videosJson!!.get(KEY).asString
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    companion object {
        private val ID = "id"
        private val KEY = "key"
    }
}