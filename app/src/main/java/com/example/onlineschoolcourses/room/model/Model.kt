package com.example.onlineschoolcourses.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "model")
data class Model(
@PrimaryKey(autoGenerate = true)
val id:Int?=null
)