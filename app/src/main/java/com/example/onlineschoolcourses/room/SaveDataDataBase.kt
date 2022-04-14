package com.example.onlineschoolcourses.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.onlineschoolcourses.room.model.Model
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.adaper.model.PersonalAreaMyCourseModel
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.model.PersonalAreaSaveDataModel


@Database(
    entities = [PersonalAreaSaveDataModel::class],
    version = 1, exportSchema = false
)
abstract class SaveDataDataBase : RoomDatabase() {

    abstract fun saveDataDao(): SaveDataDao

}