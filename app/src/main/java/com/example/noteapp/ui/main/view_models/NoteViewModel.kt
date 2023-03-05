package com.example.noteapp.ui.main.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.ui.main.models.NotesModel
import com.example.noteapp.ui.main.data.local.repositoreis.NoteRepository
import com.example.noteapp.ui.main.data.local.entities.NoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
): ViewModel() {

    init {
        getAllDataFromDb()
    }

    /**
     * data get from room db
     */
    private var data: MutableLiveData<List<NoteEntity>> = MutableLiveData()
    var liveData : LiveData<List<NoteEntity>> = data


    /**
     * insert note to table
     * @param noteModel : NotesModel
     */
    fun insertNoteToDatabase(noteModel: NotesModel) {
        viewModelScope.launch (Dispatchers.IO){
            repository.insertNote(noteModel)
        }
    }


    /**
     * update note in table
     * @param noteEntity : NoteEntity
     */
    fun updateNoteDatabase(noteEntity: NoteEntity) {
        viewModelScope.launch (Dispatchers.IO){
            repository.updateNote(noteEntity)
        }
    }

    fun deleteNote(noteEntity: NoteEntity) {
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteNote(noteEntity)
        }
    }

    private fun getAllDataFromDb(){
        viewModelScope.launch (Dispatchers.IO){
            repository.getAllData().collect {
                data.postValue(it)
            }
        }
    }


}