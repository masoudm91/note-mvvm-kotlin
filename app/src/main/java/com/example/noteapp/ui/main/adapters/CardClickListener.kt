package com.example.noteapp.ui.main.adapters

import android.view.View
import com.example.noteapp.ui.main.data.local.entities.NoteEntity


/**
 * handle click in note items
 */
interface CardClickListener {

    fun onItemClickListener(noteEntity: NoteEntity)

    fun onMenuItemClickListener(imageFilterButton: View, noteEntity: NoteEntity)
}