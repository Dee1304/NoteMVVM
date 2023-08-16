package com.example.notemvvm

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
class Note(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "Title") var title: String = "",
    @ColumnInfo(name = "Contents") var contents: String = ""
) {
}