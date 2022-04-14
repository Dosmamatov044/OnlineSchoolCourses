package com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saveDataModel")
data class PersonalAreaSaveDataModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    var courseNames: String = "",
    var course_image: String = "",
    var description: String = "",
    var file: String = "",
    var homeWork: String = "",
    var price: String = "",
    var profession: String = "",
    var test: String = "",
    var youtubeUrl: String = ""
)