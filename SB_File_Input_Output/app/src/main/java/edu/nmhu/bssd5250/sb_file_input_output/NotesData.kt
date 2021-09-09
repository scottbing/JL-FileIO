package edu.nmhu.bssd5250.sb_file_input_output

import android.content.Context
import androidx.annotation.Nullable
import java.util.ArrayList


class NotesData private constructor(private val mAppContext: Context) {
    //Singleton class of all not data
    interface NotesDataUpdatedListener {
        fun updateNotesDependents()
    }

    var listener: NotesDataUpdatedListener?
    var mNotes: ArrayList<Note?>? = null
    val noteList: ArrayList<Note>
    @JvmName("setListener1")

    fun setListener(notesDataUpdatedListener: NotesDataUpdatedListener?) {
        listener = notesDataUpdatedListener
    }

    fun getInstance(@Nullable c: Context): NotesData? {
        if (sNotesData == null) {
            sNotesData = NotesData(c.applicationContext)
        }
        return sNotesData
    }

    @JvmName("getNoteList1")
    fun getNoteList(): ArrayList<Note?>? {
        return mNotes
    }

    fun refreshNotes() {
        if (listener != null) listener!!.updateNotesDependents()
    }

    companion object {
        private var sNotesData: NotesData? = null
        fun getInstance(c: Context?): NotesData? {
            if (sNotesData == null) {
                sNotesData = NotesData(c!!.applicationContext)
            }
            return sNotesData
        }
    }

    init {
        noteList = ArrayList()
        listener = null
    }
}
