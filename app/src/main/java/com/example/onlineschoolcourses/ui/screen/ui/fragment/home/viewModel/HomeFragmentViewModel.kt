package com.example.onlineschoolcourses.ui.screen.ui.fragment.home.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineschoolcourses.ui.register.repository.DataStoreRepository
import com.example.onlineschoolcourses.ui.screen.ui.fragment.home.model.CommonModel
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.adaper.model.PersonalAreaMyCourseModel
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.model.RealtimeDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HomeFragmentViewModel @Inject constructor(application: Application,val _realtimeDatabaseRepository: RealtimeDatabaseRepository,repository: DataStoreRepository):AndroidViewModel(application) {
    private val _usersMaterialsData = MutableSharedFlow<MutableList<CommonModel>>()
    val usersMaterialsData=_usersMaterialsData.asSharedFlow()


init {


    viewModelScope.launch {
        repository.readKey.collect {

            fetchFromTwoTableData(it)
        }}



}

    @ExperimentalCoroutinesApi
    fun fetchFromTwoTableData(childText:String) {
        viewModelScope.launch {

            _realtimeDatabaseRepository.fetchFromTwoTableData(childText).collect {
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