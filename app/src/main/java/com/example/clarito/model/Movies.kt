package com.example.clarito

import com.google.gson.JsonObject
import java.lang.Exception
import java.text.ParseException
import java.text.SimpleDateFormat
import java.io.Serializable
import java.util.*
import kotlin.properties.Delegates

class Movie(movieJson: JsonObject?) : Serializable {

    var id: Int = 0
    lateinit var original_title: String
    lateinit var adult: String
    lateinit var backdrop_path: String
    lateinit var original_language: String
    lateinit var overview: String
    lateinit var popularity: String
    lateinit var poster_path: String
    lateinit var release_date: String
    lateinit var title: String
    lateinit var video: String
    lateinit var vote_average: String
    lateinit var vote_count: String

    init {
        try {
            id                      = movieJson!!.get(ID).asInt
            original_title          = movieJson!!.get(ORIGINAL_TITLE).asString
            title                   = movieJson!!.get(TITLE).asString
            adult                   = movieJson!!.get(ADULT).asString
            backdrop_path           = movieJson!!.get(BACKDROP_PATH).asString
            original_language       = movieJson!!.get(ORIGINAL_LENGUAJE).asString
            overview                = movieJson!!.get(OVERVIEW).asString
            popularity              = movieJson!!.get(POPULARITY).asString
            poster_path             = movieJson!!.get(POSTER_PATH).asString
            release_date            = getFormatDate(movieJson!!.get(RELEASE_DATE).asString)
            video                   = movieJson!!.get(VIDEO).asString
            vote_average            = getFormatVote(movieJson!!.get(VOTE_AVERAGE).asString)
            vote_count              = movieJson!!.get(VOTE_COUNT).asString
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    companion object {
        private val ID                  = "id"
        private val ORIGINAL_TITLE      = "original_title"
        private val TITLE               = "title"
        private val ADULT               = "adult"
        private val BACKDROP_PATH       = "backdrop_path"
        private val ORIGINAL_LENGUAJE   = "original_language"
        private val OVERVIEW            = "overview"
        private val POPULARITY          = "popularity"
        private val POSTER_PATH         = "poster_path"
        private val RELEASE_DATE        = "release_date"
        private val VIDEO               = "video"
        private val VOTE_AVERAGE        = "vote_average"
        private val VOTE_COUNT          = "vote_count"
    }

    private  fun getFormatVote(voteAverage: String):String {
        var intVote : Float = voteAverage.toFloat()
        var intvote = Math.floor((intVote * 10).toDouble()).toInt()
        return ""+intvote+"%"
    }

    private fun getFormatDate(releaseDate:String):String {
        val format = SimpleDateFormat("yyyy-MM-dd")
        val dateFormat = SimpleDateFormat("dd MMMM yyyy")
        try {
            val parsedDateFormat = format.parse(releaseDate)
            val cal = Calendar.getInstance()
            cal.time = parsedDateFormat
            return dateFormat.format(cal.time)
        } catch (e: ParseException) {
            e.printStackTrace()
            return ""
        }
    }
}