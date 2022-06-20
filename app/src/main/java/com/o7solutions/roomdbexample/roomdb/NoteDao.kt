package com.o7solutions.roomdbexample.roomdb

import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    fun saveData(note: NoteEntity)

    @Query("select * from noteentity")
    fun getData(): List<NoteEntity>

    @Delete
    fun deleteNote(note: NoteEntity)

    @Update
    fun updateNote(note: NoteEntity)
}