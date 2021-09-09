package edu.nmhu.bssd5250.sb_file_input_output

import android.content.Context
import org.json.JSONArray
import org.json.JSONException
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception
import java.util.ArrayList


class NoteJSONSerializer     // NoteJSONSerializer Constructor
    (private val mContext: Context, private val mFilename: String) {
    @Throws(JSONException::class, IOException::class)
    fun saveNotes(notes: ArrayList<Note?>?) {
        //Build an array in JSON
        val jsonArray = JSONArray()
        if (notes != null) {
            for (n in notes) {
                //use  the toJSON function we wrote on each note
                if (n != null) {
                    jsonArray.put(n.toJSON())
                }
            }
        }
        //JSONArray of all notes built
        try {
            writeDataFile(jsonArray)
        } catch (e: Exception) {
            //catch any errors that occur from try and throw them back to whoever called this
            throw e
        }
    }

    @Throws(JSONException::class, IOException::class)
    private fun writeDataFile(jsonArray: JSONArray) {
        val notes: ArrayList<Note?>? = NotesData.getInstance(mContext)?.getNoteList()
        val file = File(mContext.filesDir, mFilename)
        val fileOutputStream: FileOutputStream
        try {    //try to open the  file for writing
            fileOutputStream = FileOutputStream(file)
            //Turn the first note's name to bytes   in utf8 format and put in file
            fileOutputStream.write(jsonArray.toString().toByteArray(charset("UTF-8")))
            fileOutputStream.flush()
            fileOutputStream.close()
        } catch (e: Exception) {
            //catch any errors that occur from try and throw them back to whoever called this
            throw e
        }
    }
}
