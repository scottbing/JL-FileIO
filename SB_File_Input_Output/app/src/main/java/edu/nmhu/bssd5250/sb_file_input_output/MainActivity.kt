package edu.nmhu.bssd5250.sb_file_input_output

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.Exception
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main);
        makeData()
        //writeDataFile();
        val noteJSONSerializer = NoteJSONSerializer(this, "notes.json")
        try {
            noteJSONSerializer.saveNotes(NotesData.getInstance(this)?.getNoteList())
        } catch (e: Exception) {
            Log.d(LOGID, e.toString())
        }
        readDataFile("notes json")
    }

    private fun makeData() {
        val notes: ArrayList<Note?>? = NotesData.getInstance(this)?.getNoteList()
        for (i in 0..9) {
            val note = Note()
            note.name = "Note	#$i"
            note.desc = View.generateViewId().toString()
            if (notes != null) {
                notes.add(note)
            }
        }
    }

    private fun writeDataFile() {
        val notes: ArrayList<Note?>? = NotesData.getInstance(this)?.getNoteList()
        val filename = "notes.txt"
        val file = File(applicationContext.filesDir, filename)
        val fileOutputStream: FileOutputStream
        try {    //try to open the  file for writing
            fileOutputStream = FileOutputStream(file)
            //Turn the first note's name to bytes   in utf8 format and put in file
            fileOutputStream.write(notes?.get(0).toString().toByteArray(charset("UTF-8")))
            fileOutputStream.flush()
            fileOutputStream.close()
        } catch (e: Exception) {
            //catch any errors that occur from try and throw them back to whoever called this
            Log.d(LOGID, e.toString())
        }
    }

    private fun readDataFile(filename: String) {
        val file = File(applicationContext.filesDir, filename)
        val length = file.length().toInt()
        Log.d(LOGID, "File is bytes: $length")
        val bytes = ByteArray(length) //byte array to hold all read bytes
        val fileInputStream: FileInputStream
        try { //try to open the file for reading
            fileInputStream = FileInputStream(file)
            fileInputStream.read(bytes)
            fileInputStream.close()
        } catch (e: Exception) { //handle exception arising from above
            Log.d(LOGID, e.toString())
        }

        //Now try something different that also requires eception handling
        try {
            val s = String(bytes, Charsets.UTF_8)
            Log.d(LOGID, s)
        } catch (e: Exception) { //handle exception from string creation
            Log.d(LOGID, e.toString())
        }
    }

    companion object {
        private const val LOGID = "MainActivity"
    }
}
