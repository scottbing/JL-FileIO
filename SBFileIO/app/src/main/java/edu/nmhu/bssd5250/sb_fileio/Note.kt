package edu.nmhu.bssd5250.sb_fileio

import org.json.JSONObject
import java.util.*

class Note(var name:String, var desc:String, var date: String?) {

    init{
        if (date == null) {
            date = Date().toString()
        }
    }

    fun toJSON(): JSONObject {
        //make a new json object
        val jsonObject = JSONObject().apply {
            //put each piece of data inot the object
            put("name", name)
            put("date", date)
            put("desc", desc)
        }
        return jsonObject
    }

    override fun toString(): String {
        return "$name, $date, $desc"
    }
}