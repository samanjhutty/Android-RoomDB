package com.o7solutions.roomdbexample.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0

    @ColumnInfo(name = "Title")
     var title: String? = null

    @ColumnInfo(name = "Description")
     var desc: String?=null
}