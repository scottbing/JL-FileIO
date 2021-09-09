package edu.nmhu.bssd5250.sb_fileio

import android.content.Context

class NotesData(context: Context) {

    private var mContext:Context? = null
    private val notesData:ArrayList<Note> = ArrayList<Note>()

    init{
        mContext = context
    }
    fun addnote(note: Note) {
        notesData.add(note)
    }

    fun getNotelist():ArrayList<Note> {
        return notesData
    }

    override fun toString(): String {
        var allNotes = ""
        for (note in notesData) {
            allNotes += note.toString()
        }
        return allNotes
    }
}