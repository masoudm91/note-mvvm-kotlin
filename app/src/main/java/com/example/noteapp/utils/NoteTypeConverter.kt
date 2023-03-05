package com.example.noteapp.utils


import androidx.room.TypeConverter
import com.example.noteapp.ui.main.models.NotesModel

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * convert model to json & json to model
 */
class NoteTypeConverter {


    @TypeConverter
    fun toJson(notesModel: NotesModel): String? {
        if (notesModel == null) return null

        val gson = Gson()
        val type: Type = object : TypeToken<NotesModel?>() {}.type
        return gson.toJson(notesModel, type)
    }

    @TypeConverter
    fun toDataClass(note: String?): NotesModel? {
        if (note == null) return null
        val gson = Gson()
        val type: Type = object : TypeToken<NotesModel?>() {}.type
        return gson.fromJson(note, type)
    }
}