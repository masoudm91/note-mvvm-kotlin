package com.example.noteapp.ui.main.data.local.repositoreis


import com.example.noteapp.ui.main.models.NotesModel
import com.example.noteapp.room.AppDatabase
import com.example.noteapp.ui.main.data.local.entities.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Database CURD repository
 */
class NoteRepository @Inject constructor(
    appDatabase: AppDatabase
){

    private val roomDao = appDatabase.roomDao()

    fun insertNote(noteModel: NotesModel) {
        // uid is auto generate & primary key
        val noteEntity = NoteEntity(0 ,noteModel)
        roomDao.insert(noteEntity)
    }

    fun updateNote(noteEntity: NoteEntity) {
        roomDao.update(noteEntity)
    }

    fun deleteNote(noteEntity: NoteEntity) {
        roomDao.delete(noteEntity)
    }

    fun getAllData(): Flow<List<NoteEntity>> {
        return roomDao.getAll()
    }
}