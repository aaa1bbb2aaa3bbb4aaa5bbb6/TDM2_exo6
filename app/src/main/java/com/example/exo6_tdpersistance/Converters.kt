package com.example.exo6_tdpersistance

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


/**
 * Created by HichemBennaceur on 1/05/2020.
 */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    object Converters {
        /*

        This method takes our arraylist object as parameter and
        returns string representation for it so that
         it can be stored in Room Database.
         */
        @TypeConverter
        fun fromString(value: String?): ArrayList<String> {
            val listType: Type =
                object : TypeToken<ArrayList<String?>?>() {}.getType()
            return Gson().fromJson(value, listType)
        }
        /*
        While reading data back from Room Database, we get JSON
        form of our arraylist which we need to convert back.
        We will use Gson method fromJson
        by providing JSON string as parameter
         */
        @TypeConverter
        fun fromArrayList(list: ArrayList<String?>?): String {
            val gson = Gson()
            return gson.toJson(list)
        }
    }
}