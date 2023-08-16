package com.example.notemvvm

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContentViewModel(var mContext: Context): ViewModel() {
    private val db = NoteDB.getInstance(mContext)
    var id = MutableLiveData<Int>()
    var title= ""
    var contents = ""

    fun onClickAdd(){
        if (id.value == -1){
            val note = Note(null, title, contents)
            db.noteDao().insert(note)
            mContext.startActivity(Intent(mContext, MainActivity::class.java))
        }
        else{
            val note = Note(id.value, title, contents)
            db.noteDao().update(note)
            id = MutableLiveData<Int>()
            mContext.startActivity(Intent(mContext, MainActivity::class.java))
        }
    }
}