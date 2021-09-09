package edu.nmhu.bssd5250.sb_fileio

import android.content.Context
import android.util.Log
import org.json.JSONArray

class NotesData(context: Context) {

    private var mContext:Context? = null
    private val notesData:ArrayList<Note> = ArrayList()

    init{
        mContext = context
    }
    fun addNote(note: Note) {
        notesData.add(note)
    }

    fun toJSON():JSONArray {
        val jsonArray = JSONArray()
        for (note in notesData) {
            jsonArray.put(note.toJSON())
        }
        return jsonArray
    }

    fun loadNotes(data:JSONArray) {
        Log.d("NotesData", data.length().toString())
        for(i in 0 until data.length()) {
            val obj = data.getJSONObject(i)
            addNote(
                (Note(
                    obj.getString("name"),
                    obj.getString("date"),
                    obj.getString("desc")
                ))
            )
        }
    }

    override fun toString(): String {
        var allNotes = ""
        for (note in notesData) {
            allNotes += note.toString()
        }
        return allNotes
    }
}