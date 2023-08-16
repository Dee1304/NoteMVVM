package com.example.notemvvm

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(var mContext: Context): ViewModel() {

    private val db = NoteDB.getInstance(mContext)

    val listLive = MutableLiveData<MutableList<Note>>()

    fun delete(note: Note){
        db.noteDao().delete(note)
    }

    fun getData(): MutableLiveData<MutableList<Note>> {
        val lst = db.noteDao().getAll()
        lst.sortByDescending {
            it.id
        }
        listLive.value = lst
        return listLive
    }

    fun onClickFloating(){
        mContext.startActivity(Intent(mContext, ContentActivity::class.java))
    }
}