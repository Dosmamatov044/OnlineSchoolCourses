package com.example.onlineschoolcourses.room

import androidx.room.*
import com.example.onlineschoolcourses.room.model.Model
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.adaper.model.PersonalAreaMyCourseModel
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.model.PersonalAreaSaveDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface SaveDataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(mAdd:MutableList<PersonalAreaSaveDataModel>)

    @Delete
    suspend fun delete(mDelete: PersonalAreaSaveDataModel)

    @Query("SELECT * FROM saveDataModel ORDER BY profession  ASC ")
    fun getAll(): Flow<List<PersonalAreaSaveDataModel>>

   // @Query("SELECT * FROM model WHERE name LIKE :searchQuery OR number LIKE :searchQuery")
   // fun searchDatabase(searchQuery: String): Flow<List<Model>>

   // @Query("SELECT * FROM model WHERE `from` LIKE :from")
   // fun getFrom(from: String): Flow<List<Model>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateId(mInsert: PersonalAreaSaveDataModel)


}