package com.example.jl_fileio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String LOGID = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        makeData();
        //writeDataFile();
        NoteJSONSerializer noteJSONSerializer = new NoteJSONSerializer( this, "notes.json" );
        try {
            noteJSONSerializer.saveNotes (NotesData.getInstance(this).getNoteList());
        }  catch (Exception e) {
            Log.d(LOGID, e.toString());
        }
        readDataFile("notes json");
    }

    private void makeData() {
        ArrayList<Note> notes = NotesData.getInstance(this).getNoteList();
        for(int i=0;  i<10;  i++) {
            Note note = new Note();
            note.setName("Note	#" + i);
            note.setDesc(String.valueOf(View.generateViewId()));
            notes.add(note);
        }
    }


    private void writeDataFile() {
        ArrayList<Note> notes = NotesData.getInstance(this).getNoteList();
        String filename = "notes.txt";
        File file = new File(getApplicationContext().getFilesDir(), filename);
        FileOutputStream fileOutputStream;
        try {    //try to open the  file for writing
            fileOutputStream = new FileOutputStream(file);
            //Turn the first note's name to bytes   in utf8 format and put in file
            fileOutputStream.write(notes.get(0).toString().getBytes("UTF-8"));
            fileOutputStream.flush();
            fileOutputStream.close();
            } catch (Exception e) {
                //catch any errors that occur from try and throw them back to whoever called this
                Log.d(LOGID, e.toString());
            }
        }

    private void readDataFile(String filename)  {

        File file = new File(getApplicationContext().getFilesDir(), filename);
        int length = (int)file.length();
        Log.d(LOGID,"File is bytes: " + String.valueOf(length));

        byte[] bytes = new byte[length]; //byte array to hold all read bytes

        FileInputStream fileInputStream;
        try { //try to open the file for reading
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytes);
            fileInputStream.close();
        } catch (Exception e) {//handle exception arising from above
            Log.d(LOGID, e.toString());
        }

        //Now try something different that also requires eception handling
        try {
            String s = new String(bytes,"UTF-8");
            Log.d(LOGID, s);
        } catch (Exception e) { //handle exception from string creation
            Log.d(LOGID, e.toString());
        }
    }

}
