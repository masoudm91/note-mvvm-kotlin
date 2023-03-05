package com.example.noteapp.room

import androidx.room.*
import com.example.noteapp.ui.main.data.local.entities.NoteEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RoomDao {

    @Query("SELECT * FROM notetable")
    fun getAll(): Flow<List<NoteEntity>>

    @Insert
    fun insert(noteEntity: NoteEntity)

    @Delete
    fun delete(noteEntity: NoteEntity)

    @Update
    fun update(noteEntity: NoteEntity)
}