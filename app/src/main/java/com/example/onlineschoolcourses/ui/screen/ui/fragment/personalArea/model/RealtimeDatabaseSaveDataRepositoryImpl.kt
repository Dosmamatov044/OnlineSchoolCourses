package com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.model

import com.example.onlineschoolcourses.room.SaveDataDao
import javax.inject.Inject

class RealtimeDatabaseSaveDataRepositoryImpl @Inject constructor(val saveDataDao: SaveDataDao) {

    suspend fun insert(insert:MutableList <PersonalAreaSaveDataModel>) = saveDataDao.insert(insert)
    suspend fun delete(delete: PersonalAreaSaveDataModel) = saveDataDao.delete(delete)
    fun getAll() =saveDataDao.getAll()


}