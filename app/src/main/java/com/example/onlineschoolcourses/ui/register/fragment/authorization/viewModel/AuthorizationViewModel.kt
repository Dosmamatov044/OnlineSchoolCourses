package com.example.onlineschoolcourses.ui.register.fragment.authorization.viewModel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onlineschoolcourses.ui.register.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(application: Application,var authRepository: AuthRepository):AndroidViewModel(application) {
    private var _userMutableLiveData= MutableLiveData<FirebaseUser>()
    val userMutableLiveData: LiveData<FirebaseUser>
        get() = _userMutableLiveData

    init {
        authRepository= AuthRepository(getApplication())

        _userMutableLiveData=authRepository.userMutableLiveData
    }



    @RequiresApi(Build.VERSION_CODES.P)
    fun entrance(email:String, password:String){
        authRepository.entrance(email,password)

    }





}