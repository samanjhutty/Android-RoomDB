package com.o7solutions.roomdbexample.roomdb

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(NoteEntity::class),version = 1)
abstract class AppDb : RoomDatabase(){
    abstract fun noteDao():NoteDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDb?=null

        fun getDatabase(context: Context): AppDb {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val xyz = Room.databaseBuilder(
                    context.applicationContext,
                    AppDb::class.java,
                    "rajacool"
                ).build()
                INSTANCE = xyz
                // return instance
                xyz
            }
        }

    }

}