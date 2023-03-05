package com.example.noteapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.noteapp.ui.main.data.local.entities.NoteEntity
import com.example.noteapp.utils.NoteTypeConverter

/**
 * config room db
 */
@TypeConverters(NoteTypeConverter::class)
@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun roomDao():RoomDao
}