
package edu.nmhu.bssd5250.sb_fileio

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.json.JSONArray
import java.io.*

class MainActivity : AppCompatActivity() {

    private lateinit var notesData:NotesData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        Log.i("MainActivity file", "in onCreate()")
        makeData()
    }

    override fun onPostResume() {
        super.onPostResume()
        val jsonArray = readDataAsJSON()
        if(jsonArray != null) {
            loadJSONNotes(jsonArray)
        } else {
            makeData()
        }
    }

    override fun onPause() {
        super.onPause()
        writeDataToFile(notesData)
    }

    private fun makeData() {
        Log.i("MainActivity file", "in makeData()")
        notesData = NotesData(applicationContext)
        for (i in 1..5){
            val todo = Note("Note $1",
                "For the birthday party", null)
            notesData.addNote(todo)
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
        val data:String = notesData.toJSON().toString()
        //val fileOutputStream: FileOutputStream
        try {
            val fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
            fileOutputStream.write(data.toByteArray())
            fileOutputStream.flush()
            fileOutputStream.close()
            Log.i("MainActivity file", notesData.toString())
        }catch (e: Exception){
            e.printStackTrace()
        }

    }

    private fun readDataAsJSON(): JSONArray? {
        try {
            Log.i("MainActivity file", "in readData()")
            val fileInputStream: FileInputStream? = openFileInput("notes.json")
            val inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()
            var text: String? = null
            while (run {
                    text = bufferedReader.readLine()
                    text
                } != null) {
                stringBuilder.append(text)
            }
            fileInputStream?.close()
            return JSONArray(stringBuilder.toString())
        } catch(e:FileNotFoundException) {
            return null
        }

    }

    private fun loadJSONNotes(data: JSONArray) {
        val notesData2 = NotesData(applicationContext).apply  {
            loadNotes(data)
        }
        Log.d("MainActivity reuslt", notesData2.toString())
    }
}
