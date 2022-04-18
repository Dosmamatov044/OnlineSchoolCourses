package com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.onlineschoolcourses.ui.register.repository.DataStoreRepository
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.adaper.model.PersonalAreaMyCourseModel
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.model.PersonalAreaSaveDataModel
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.model.RealtimeDatabaseRepository
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.model.RealtimeDatabaseSaveDataRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PersonalAreaViewModel @Inject constructor(private val _realtimeDatabaseRepository: RealtimeDatabaseRepository,
                                                var repository: DataStoreRepository,val realtimeDatabaseSaveDataRepositoryImpl: RealtimeDatabaseSaveDataRepositoryImpl,application: Application): AndroidViewModel(application) {

    //val usersMaterialsData = MutableLiveData<MutableList<PersonalAreaMyCourseModel>>()
   private val _usersMaterialsData = MutableSharedFlow<MutableList<PersonalAreaMyCourseModel>>()
val usersMaterialsData=_usersMaterialsData.asSharedFlow()

    init {


        viewModelScope.launch {
repository.readKey.collect {

    fetchNotifications(it)
}}


        

    }
    fun insertId(insert:MutableList< PersonalAreaSaveDataModel>) = viewModelScope.launch {
        realtimeDatabaseSaveDataRepositoryImpl.insert(insert)
    }
    fun deleteId(delete: PersonalAreaSaveDataModel) = viewModelScope.launch {
        realtimeDatabaseSaveDataRepositoryImpl.delete(delete)
    }

    val allData = realtimeDatabaseSaveDataRepositoryImpl.getAll().asLiveData()

    @ExperimentalCoroutinesApi
    fun fetchNotifications(childText:String) {
        viewModelScope.launch {

            _realtimeDatabaseRepository.fetchUserMaterials(childText).collect {
                when {
                    it.isSuccess -> {
                        it.getOrNull()?.let { it1 ->


                            _usersMaterialsData.emit(it1) }



                    }
                    it.isFailure -> {
                        it.exceptionOrNull()?.printStackTrace()
                    }
                }
            }
        }
    }




}