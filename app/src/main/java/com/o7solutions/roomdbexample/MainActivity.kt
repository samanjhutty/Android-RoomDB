package com.o7solutions.roomdbexample

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.o7solutions.roomdbexample.fragments.ShowNoteFragment
import com.o7solutions.roomdbexample.roomdb.AppDb
import com.o7solutions.roomdbexample.roomdb.NoteDao
import com.o7solutions.roomdbexample.roomdb.NoteEntity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.MyContainer,ShowNoteFragment()).commit()
        findViewById<Button>(R.id.btnSave).setOnClickListener {
            enteredData()
            supportFragmentManager.beginTransaction().replace(R.id.MyContainer,ShowNoteFragment()).commit()
        }
        findViewById<Button>(R.id.btn_shared_preference).setOnClickListener{

            val a=Intent(this,AnotherActivity::class.java)
            startActivity(a)
        }
    }

    fun enteredData(){

        class saveData: AsyncTask<Void, Void, Void>()
        {
            override fun doInBackground(vararg p0: Void?): Void? {
                val note=NoteEntity()
                note.title="rajacool"
                note.desc="raja is cool"

                AppDb.getDatabase(this@MainActivity).noteDao().saveData(note)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(this@MainActivity,"data inserted!",Toast.LENGTH_SHORT).show()
            }
        }
        saveData().execute()
    }
}