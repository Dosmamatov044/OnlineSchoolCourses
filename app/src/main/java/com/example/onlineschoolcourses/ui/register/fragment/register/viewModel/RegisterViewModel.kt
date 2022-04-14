package com.example.onlineschoolcourses.ui.register.fragment.register.viewModel

import android.app.Application
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.onlineschoolcourses.helpers.myRef
import com.example.onlineschoolcourses.helpers.replaceFragmentScreen
import com.example.onlineschoolcourses.ui.register.fragment.additionalInformation.AdditionalInformationAboutUserFragment
import com.example.onlineschoolcourses.ui.register.repository.AuthRepository
import com.example.onlineschoolcourses.ui.register.repository.DataStoreRepository
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    application: Application,
    var authRepository: AuthRepository,
    var dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {
    val database = Firebase.database
    private var _userMutableLiveData = MutableLiveData<FirebaseUser>()
    val userMutableLiveData: LiveData<FirebaseUser>
       get() = _userMutableLiveData

    init {
        authRepository = AuthRepository(getApplication())
        dataStoreRepository = DataStoreRepository(getApplication())
        _userMutableLiveData = authRepository.userMutableLiveData
    }


    @RequiresApi(Build.VERSION_CODES.P)
    fun register(password: String, email: String) {
        authRepository.register(password,email )

    }


    fun saveKey(key: String) = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.saveKey(key)
    }


    fun uploadDatabase(name:String,surname:String,email: String,firebaseUser: FirebaseUser) {
        myRef = database.reference.child("users")
        val key = myRef.push().key.toString()


            if (name.isNotEmpty() && surname.isNotEmpty() && email.isNotEmpty()) {
                val user = HashMap<String, String>()
                user["key"] = key
                user["email"] = email
                user["name"] = name
                user["surname"] = surname
                myRef.child(key).setValue(user).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(getApplication(),"успех",Toast.LENGTH_LONG).show()
                        saveKey(key)
                        replaceFragmentScreen(AdditionalInformationAboutUserFragment())

                    } else {
                        Toast.makeText(getApplication(),it.exception?.message.toString(),Toast.LENGTH_LONG).show()
                    }
                }.addOnFailureListener {
                    Log.d("ololo", it.message.toString())
                }
            } else {
                Toast.makeText(getApplication(),"Заполните поля",Toast.LENGTH_LONG).show()


            }

        }
    }




