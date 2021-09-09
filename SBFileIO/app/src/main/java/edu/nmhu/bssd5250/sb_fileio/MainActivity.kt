
package edu.nmhu.bssd5250.sb_fileio

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        Log.i("MainActivity file", "in onCreate()")
        makeData()
        readData()
    }
    private fun makeData() {
        Log.i("MainActivity file", "in makeData()")
        val notesData = NotesData(applicationContext)
        for (i in 1..5){
            val todo = Note("Note $1", "For the birthday party", null)
        }
        writeDataToFile(notesData)
    }

    private fun readData() {
        Log.i("MainActivity file", "in readData()")
        var fileInputStream: FileInputStream? = null
        fileInputStream = openFileInput("notes.json")
        var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder: StringBuilder = StringBuilder()
        var text: String? = null
        while ({ text = bufferedReader.readLine(); text }() != null) {
            stringBuilder.append(text)
        }
        fileInputStream.close()
        Log.i("MainActivity file", stringBuilder.toString())
    }

    private fun writeDataToFile(notesData: NotesData) {
        Log.i("MainActivity file", "in writeDataToFile()")
        val file:String = "notes.json"
        val data:String = notesData.toString()
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
            fileOutputStream.write(data.toByteArray())
            fileOutputStream.flush()
            fileOutputStream.close()
            Log.i("MainActivity file", notesData.toString())
        }catch (e: Exception){
            e.printStackTrace()
        }

    }
}
