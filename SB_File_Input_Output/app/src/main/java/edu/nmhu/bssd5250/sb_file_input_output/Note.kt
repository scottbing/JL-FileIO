package edu.nmhu.bssd5250.sb_file_input_output

import org.json.JSONException
import org.json.JSONObject
import java.util.*


class Note {
    var name: String? = null
    var date: String
    var desc: String? = null

    @Throws(JSONException::class)
    fun toJSON(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("name", name)
        jsonObject.put("date", date)
        jsonObject.put("desc", desc)
        return jsonObject
    }

    init {
        // default to today's date
        date = Date().toString()
    }
}


