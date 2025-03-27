package com.geeks.noteapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geeks.noteapp.data.database.dao.NoteDao
import com.geeks.noteapp.data.models.Note

@Database(entities = [Note::class  ], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun noteDao(): NoteDao
}
